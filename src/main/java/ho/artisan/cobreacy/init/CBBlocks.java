package ho.artisan.cobreacy.init;

import ho.artisan.cobreacy.Cobreacy;
import ho.artisan.cobreacy.api.block.MillingStoneBlock;
import ho.artisan.cobreacy.api.fluid.FlowingCreblankFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CBBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, Cobreacy.MODID);

    public static final RegistryObject<LiquidBlock> CREBLANK_FLUID_BLOCK;
    public static final RegistryObject<Block> MILLING_STONE;

    static {
        CREBLANK_FLUID_BLOCK = BLOCKS.register("creblank_fluid_block", () -> new FlowingCreblankFluid(CBFluids.CREBLANK_FLUID, BlockBehaviour.Properties.of().noCollission().strength(100.0F).noLootTable().replaceable().liquid().noOcclusion().pushReaction(PushReaction.DESTROY)));
        MILLING_STONE = BLOCKS.register("milling_stone", () -> new MillingStoneBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    }
}
