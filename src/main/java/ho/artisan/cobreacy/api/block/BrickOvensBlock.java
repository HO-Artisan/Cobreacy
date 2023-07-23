package ho.artisan.cobreacy.api.block;

import ho.artisan.cobreacy.api.block.entity.BrickOvensBlockEntity;
import ho.artisan.cobreacy.init.CBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class BrickOvensBlock extends BaseEntityBlock {
    public BrickOvensBlock() {
        super(Properties.copy(Blocks.BRICKS));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BrickOvensBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand playerHand, BlockHitResult result) {
        if (player.getItemInHand(playerHand).getItem() == Items.AIR && player.isShiftKeyDown()) {
            var shapeFind = getOrCreatePortalShape().find(level, pos);
            if (shapeFind != null) {
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    public static BlockPattern getOrCreatePortalShape() {
        return BlockPatternBuilder.start()
                .aisle("AAA", "IBI")
                .aisle("AAA", "AAA")
                .aisle("AFA", "ACA")
                .where('A', BlockInWorld.hasState(BlockStatePredicate.ANY))
                .where('F', BlockInWorld.hasState(BlockStatePredicate.forBlock(CBBlocks.FUEL_HATCH.get())))
                .where('B', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.STONE_BRICKS)))
                .where('C', BlockInWorld.hasState(BlockStatePredicate.forBlock(CBBlocks.BRICK_OVENS.get())))
                .build();
    }
}
