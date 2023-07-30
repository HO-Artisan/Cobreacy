package ho.artisan.cobreacy.init;

import ho.artisan.cobreacy.Cobreacy;
import ho.artisan.cobreacy.impl.recipe.MillingStoneRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CBRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Cobreacy.MODID);

    public static final RegistryObject<RecipeType<MillingStoneRecipe>> MILLING_STONE;

    static {
        MILLING_STONE = RECIPE_TYPES.register("milling_stone", () -> new RecipeType<>() {
            @Override
            public String toString() {
                return Cobreacy.MODID + ":" + "milling_stone";
            }
        });
    }
}
