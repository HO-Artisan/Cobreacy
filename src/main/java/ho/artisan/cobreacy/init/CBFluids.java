package ho.artisan.cobreacy.init;

import ho.artisan.cobreacy.Cobreacy;
import ho.artisan.cobreacy.api.fluid.FermentationFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CBFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Cobreacy.MODID);

    public static final RegistryObject<FlowingFluid> FERMENTATION_FLUID;
    public static final RegistryObject<FlowingFluid> FLOWING_FERMENTATION_FLUID;

    static {
        FERMENTATION_FLUID = FLUIDS.register("fermentation_fluid", FermentationFluid.Source::new);
        FLOWING_FERMENTATION_FLUID = FLUIDS.register("flowing_fermentation_fluid", FermentationFluid.Flowing::new);
    }

}
