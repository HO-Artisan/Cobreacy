package ho.artisan.cobreacy.api.fluid;

import ho.artisan.cobreacy.init.CBBlocks;
import ho.artisan.cobreacy.init.CBFluids;
import ho.artisan.cobreacy.init.CBItems;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class FermentationFluid extends ForgeFlowingFluid {
    protected FermentationFluid() {
        super(new Properties(() -> new FluidType(FluidType.Properties.create()), CBFluids.FERMENTATION_FLUID, CBFluids.FLOWING_FERMENTATION_FLUID)
                .bucket(CBItems.FERMENTATION_FLUID_BUCKET).block(CBBlocks.FERMENTATION_FLUID_BLOCK));
    }

    public static class Flowing extends FermentationFluid {
        public Flowing()
        {
            super();
            registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
        }

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends FermentationFluid {
        public Source()
        {
            super();
        }

        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }


}
