package ho.artisan.cobreacy.api.block;

import ho.artisan.cobreacy.init.CBFluids;
import ho.artisan.cobreacy.init.CBItems;
import ho.artisan.cobreacy.init.CBTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class CreblankFluidBlock extends LiquidBlock {
    public CreblankFluidBlock() {
        super(CBFluids.CREBLANK_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY));
    }


    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity item) {
            if (item.getItem().is(CBTags.FLOORS)) {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 0);
                item.remove(Entity.RemovalReason.KILLED);
                var i = item.getItem().getItem();
                if (i == CBItems.BLAZE_POWDER_SACK.get()) level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(CBItems.BLAZE_POWDER_BLANK.get())));
                else if (i == CBItems.SLIME_SACK.get()) level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(CBItems.SLIME_BLANK.get())));
                else if (i == CBItems.NETHER_WART_POWDER_SACK.get()) level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(CBItems.NETHER_WART_BLANK.get())));
            }
        }
    }
}
