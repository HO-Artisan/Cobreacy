package ho.artisan.cobreacy.api.block;

import ho.artisan.cobreacy.init.CBFluids;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class CreblankFluidBlock extends LiquidBlock {
    public CreblankFluidBlock() {
        super(CBFluids.CREBLANK_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY));
    }
}
