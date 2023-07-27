package ho.artisan.cobreacy.api.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ho.artisan.cobreacy.init.CBRecipeTypes;
import ho.artisan.cobreacy.init.event.CBRecipeSerializers;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Goulixiaoji
 */
public class MillingStoneRecipe implements Recipe<RecipeWrapper> {
    private final ResourceLocation id;
    private final String group;
    private final Ingredient input;
    private final Ingredient output;
    private final String soundEvent;
    public MillingStoneRecipe(ResourceLocation id, String group, Ingredient input, Ingredient output, String soundEvent) {
        this.id = id;
        this.group = group;
        this.input = input;
        this.output = output;
        this.soundEvent = soundEvent;
    }
    @Override
    public boolean isSpecial() {
        return true;
    }

    @NotNull
    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @NotNull
    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public boolean matches(RecipeWrapper container, @NotNull Level level) {
        if (container.isEmpty()) {
            return false;
        }
        return input.test(container.getItem(0));
    }

    @NotNull
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.input);
        return nonnulllist;
    }

    @NotNull
    @Override
    public ItemStack assemble(@NotNull RecipeWrapper inv, @NotNull RegistryAccess access) {
        return this.output.getItems()[1];//this.results.get(0).getStack().copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @NotNull
    @Override
    public ItemStack getResultItem(@NotNull RegistryAccess registryAccess) {
        return this.output.getItems()[1];
    }

    @NotNull
    @Override
    public RecipeSerializer<?> getSerializer() {
        return CBRecipeSerializers.MILLING.get();
    }

    @NotNull
    @Override
    public RecipeType<?> getType() {
        return CBRecipeTypes.MILLING_STONE.get();
    }

    public static class Serializer implements RecipeSerializer<MillingStoneRecipe> {
        public Serializer() {

        }

        @NotNull
        @Override
        public MillingStoneRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject serializedRecipe) {
            final String groupIn = GsonHelper.getAsString(serializedRecipe, "group", "");
            final NonNullList<Ingredient> inputItemsIn = readIngredients(GsonHelper.getAsJsonArray(serializedRecipe, "input"));
            final NonNullList<Ingredient> outputitems = readIngredients(GsonHelper.getAsJsonArray(serializedRecipe, "output"));
            final String soundID = GsonHelper.getAsString(serializedRecipe, "sound", "");
            return new MillingStoneRecipe(recipeId, groupIn, inputItemsIn.get(0), outputitems.get(0), soundID);
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();
            for (int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }
            return nonnulllist;
        }

        @Nullable
        @Override
        public MillingStoneRecipe fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String groupIn = buffer.readUtf(32767);
            Ingredient inputItemIn = Ingredient.fromNetwork(buffer);
            Ingredient outputItems = Ingredient.fromNetwork(buffer);
            String soundEventIn = buffer.readUtf();

            return new MillingStoneRecipe(recipeId, groupIn, inputItemIn, outputItems, soundEventIn);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MillingStoneRecipe recipe) {
            buffer.writeUtf(recipe.group);
            recipe.input.toNetwork(buffer);
            recipe.output.toNetwork(buffer);
            buffer.writeUtf(recipe.soundEvent);
        }
    }
}
