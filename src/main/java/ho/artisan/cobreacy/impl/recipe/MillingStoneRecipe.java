package ho.artisan.cobreacy.impl.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import ho.artisan.cobreacy.impl.block.entity.MillingStoneBlockEntity;
import ho.artisan.cobreacy.init.CBRecipeSerializers;
import ho.artisan.cobreacy.init.CBRecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.stringtemplate.v4.misc.ArrayIterator;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author Goulixiaoji
 */
public class MillingStoneRecipe implements Recipe<MillingStoneBlockEntity> {
    private final ResourceLocation id;
    private final Ingredient[] input;
    private final ItemStack output;

    public MillingStoneRecipe(ResourceLocation id, List<Ingredient> input, ItemStack output) {
        this.id = id;
        this.input = input.toArray(new Ingredient[0]);
        this.output = output;
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

    @Override
    public boolean matches(MillingStoneBlockEntity tile, @NotNull Level level) {
        Stack<ItemStack> stack = tile.getInputItems();
        if (stack.isEmpty() || this.input.length != stack.size())
            return false;
        int k = 0;
        for (int i = 0; i < this.input.length; i++) {
            if (this.input[i].test(stack.get(i)))
                k++;
        }
        return k == this.input.length;
    }

    @NotNull
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        Collections.addAll(list, this.input);
        return list;
    }

    @NotNull
    @Override
    public ItemStack assemble(@NotNull MillingStoneBlockEntity inv, @NotNull RegistryAccess access) {
        return this.output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @NotNull
    @Override
    public ItemStack getResultItem(@NotNull RegistryAccess registryAccess) {
        return this.output;
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
        public Serializer() {}

        @NotNull
        @Override
        public MillingStoneRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
            NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            }
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            return new MillingStoneRecipe(id, nonnulllist, output);
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray ingredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i), false);
                nonnulllist.add(ingredient);
            }

            return nonnulllist;
        }

        @Nullable
        @Override
        public MillingStoneRecipe fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            nonnulllist.replaceAll(ignored -> Ingredient.fromNetwork(buffer));

            ItemStack itemstack = buffer.readItem();

            return new MillingStoneRecipe(recipeId, nonnulllist, itemstack);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MillingStoneRecipe recipe) {
            buffer.writeVarInt(recipe.input.length);

            for (Ingredient i : recipe.input) {
                i.toNetwork(buffer);
            }

            buffer.writeItem(recipe.output);
        }
    }
}
