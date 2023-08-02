package ho.artisan.cobreacy.init;

import ho.artisan.cobreacy.Cobreacy;
import ho.artisan.cobreacy.impl.fluid.CreblankFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CBFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Cobreacy.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Cobreacy.MODID);

    public static final RegistryObject<FlowingFluid> CREBLANK_FLUID;
    public static final RegistryObject<FlowingFluid> FLOWING_CREBLANK_FLUID;

    static {
        CREBLANK_FLUID = FLUIDS.register("creblank_fluid", () -> new ForgeFlowingFluid.Source(CreblankFluid.makeProperties()));
        FLOWING_CREBLANK_FLUID = FLUIDS.register("flowing_creblank_fluid", () -> new ForgeFlowingFluid.Flowing(CreblankFluid.makeProperties()));
    }

}
