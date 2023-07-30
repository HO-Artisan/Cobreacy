package ho.artisan.cobreacy.utility;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ho.artisan.cobreacy.Cobreacy;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

public class RecipeUtils implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int shaking;
    static final String NAME = "shaker";
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_LIST = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Cobreacy.MODID);
    public static final RegistryObject<RecipeSerializer<RecipeUtils>> SHAKER_RECIPE = RECIPE_LIST.register(RecipeUtils.NAME, () -> Serializer.INSTANCE);

    public RecipeUtils(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, int shaking) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.shaking = shaking;
    }

    /**
     * 匹配器，用于匹配食谱是否满足要求。在对应的检查点使用以下代码呼叫这段代码：
     * 
     * ```java
        SimpleContainer container = new SimpleContainer(8);
        item.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(it -> {
            for (int i = 0; i < 8; i ++) container.addItem(it.getStackInSlot(i).copy());
        });

        Optional<RecipeUtils> match = world.getRecipeManager().getRecipeFor(RecipeUtils.Type.INSTANCE, container, world);
        match.ifPresent(it -> {
            if (tag.getInt("shake_count") > it.getShaking()) item.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itm -> {
                for (int i = 0; i < 8; i ++)
                    itm.extractItem(i, 1, false);
                itm.insertItem(8, it.getResultItem().copy(), false);
            });
        });
     * ```
     * 
     * @param container 存储器，在上述代码中取得对象，然后塞进一个新的container中发过来。
     * @param world 世界对象，用于访问世界合成表管理器（RecipeManager），对于全局的食谱无需使用。
     * @return Boolean值用于判断是否存在对应的食谱映射。
     */
    @Override
    public boolean matches(SimpleContainer container, Level world) {
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < recipeItems.size(); i ++) {
            boolean pass = false;
            for (int o = 0; o < 8; o ++) {
                if (list.contains(o)) continue;
                if (this.recipeItems.get(i).test(container.getItem(o))) {
                    if (container.getItem(o).getCount() == 1) list.add(o);
                    else container.getItem(o).shrink(1);
                    pass = true;
                    break;
                }
            }

            if (!pass) return false;
        }
        return true;
    }



    /**
     * 装配食谱，可以考虑与 #matches 配合使用。
     */
    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    /**
     * 别忘记这个！如果这个为空，会在服务器中无法揽收远端发包的食谱！
     */
    @Override
    public RecipeSerializer<?> getSerializer() {
        return SHAKER_RECIPE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public int getShaking() {
        return this.shaking;
    }

    public static class Type implements RecipeType<RecipeUtils> {
        private Type() {}
        public static final Type INSTANCE = new Type();
    }

    /**
     * 串行器，通过网路或本地得到Json对象并解析。相关实现见Gson的JsonObject实现。注意，这些不是Gson对象的解析器，而是JsonObject对象实例。
     */
    public static class Serializer implements RecipeSerializer<RecipeUtils> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Cobreacy.MODID, RecipeUtils.NAME);

        @Override
        public RecipeUtils fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            int shaking = GsonHelper.getAsInt(json, "shake_count");

            /**
             * 使用Ingredient对象缓存Json中的Tag标记或Item标记，最终会打包成一套Nonnulllist<ItemStack>。
             */
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(ingredients.size(), Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); i++) inputs.set(i, Ingredient.fromJson(ingredients.get(i)));

            return new RecipeUtils(id, output, inputs, shaking);
        }

        @Override
        public RecipeUtils fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(it -> Ingredient.fromNetwork(buf));

            ItemStack output = buf.readItem();
            int shaking = buf.readInt();
            return new RecipeUtils(id, output, inputs, shaking);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, RecipeUtils recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) ing.toNetwork(buf);

            buf.writeItemStack(recipe.getResultItem(), false);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return Serializer.ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass();
        }

        @SuppressWarnings("unchecked")
        private static <G> Class<G> castClass() {
            return (Class<G>) RecipeSerializer.class;
        }
    }
}
