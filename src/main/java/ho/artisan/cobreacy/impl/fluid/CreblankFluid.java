package ho.artisan.cobreacy.impl.fluid;

import ho.artisan.cobreacy.Cobreacy;
import ho.artisan.cobreacy.init.CBBlocks;
import ho.artisan.cobreacy.init.CBFluids;
import ho.artisan.cobreacy.init.CBItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

// https://github.com/MinecraftForge/MinecraftForge/blob/1.20.x/src/test/java/net/minecraftforge/debug/fluid/NewFluidTest.java
public class CreblankFluid {
    public static final ResourceLocation STILL = new ResourceLocation(Cobreacy.MODID, "textures/block/creblank_fiuld_still.png");
    public static final ResourceLocation FLOW = new ResourceLocation(Cobreacy.MODID, "textures/block/creblank_fiuld_flow.png");

    public static ForgeFlowingFluid.Properties makeProperties() {
        return new ForgeFlowingFluid.Properties(FLUID_TYPE, CBFluids.CREBLANK_FLUID, CBFluids.FLOWING_CREBLANK_FLUID)
                .bucket(CBItems.CREBLANK_FLUID_BUCKET).block(CBBlocks.CREBLANK_FLUID_BLOCK);
    }

    public static RegistryObject<FluidType> FLUID_TYPE = CBFluids.FLUID_TYPES.register("creblank_fiuld", () -> new FluidType(FluidType.Properties.create()) {
        @Override
        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
            consumer.accept(new IClientFluidTypeExtensions() {

                @Override
                public ResourceLocation getStillTexture() {
                    return STILL;
                }

                @Override
                public ResourceLocation getFlowingTexture() {
                    return FLOW;
                }

                //@Nullable
                @Override
                public ResourceLocation getOverlayTexture() {
                    return null;
                }
            });
        }
    });
}
