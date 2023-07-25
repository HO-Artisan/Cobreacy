package ho.artisan.cobreacy.init.event;

import ho.artisan.cobreacy.Cobreacy;
import ho.artisan.cobreacy.api.recipe.MillingStoneRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CBRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Cobreacy.MODID);
    public static final RegistryObject<RecipeSerializer<?>> MILLING = RECIPE_SERIALIZERS.register("milling", MillingStoneRecipe.Serializer::new);
}
