package ho.artisan.cobreacy.init;

import ho.artisan.cobreacy.Cobreacy;
import ho.artisan.cobreacy.api.block.BrickOvensBlock;
import ho.artisan.cobreacy.api.block.CreblankFluidBlock;
import ho.artisan.cobreacy.api.block.FuelHatchBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CBBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, Cobreacy.MODID);

    public static final RegistryObject<LiquidBlock> CREBLANK_FLUID_BLOCK;
    public static final RegistryObject<Block> FIRE_BRICKS;
    public static final RegistryObject<Block> FUEL_HATCH;
    public static final RegistryObject<Block> BRICK_OVENS;

    static {
        CREBLANK_FLUID_BLOCK = BLOCKS.register("creblank_fluid_block", CreblankFluidBlock::new);
        FIRE_BRICKS = BLOCKS.register("fire_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
        FUEL_HATCH = BLOCKS.register("fuel_hatch", FuelHatchBlock::new);
        BRICK_OVENS = BLOCKS.register("brick_ovens", BrickOvensBlock::new);
    }
}
