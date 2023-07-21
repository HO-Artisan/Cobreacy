package ho.artisan.cobreacy.init;

import ho.artisan.cobreacy.Cobreacy;
import ho.artisan.cobreacy.api.fluid.CreblankFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CBFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Cobreacy.MODID);

    public static final RegistryObject<FlowingFluid> CREBLANK_FLUID;
    public static final RegistryObject<FlowingFluid> FLOWING_CREBLANK_FLUID;

    static {
        CREBLANK_FLUID = FLUIDS.register("creblank_fluid", CreblankFluid.Source::new);
        FLOWING_CREBLANK_FLUID = FLUIDS.register("flowing_creblank_fluid", CreblankFluid.Flowing::new);
    }

}
