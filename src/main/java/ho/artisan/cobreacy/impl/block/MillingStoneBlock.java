package ho.artisan.cobreacy.impl.block;

import ho.artisan.cobreacy.impl.block.entity.MillingStoneBlockEntity;
import ho.artisan.cobreacy.init.CBItems;
import ho.artisan.cobreacy.utility.ItemUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author Goulixiaoji
 */
public class MillingStoneBlock extends BaseEntityBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 9.0D, 14.0D);

    public MillingStoneBlock(Properties arg) {
        super(arg);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stackInHand = player.getItemInHand(hand);
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof MillingStoneBlockEntity tile) {
            Stack<ItemStack> input = tile.getInputItems();
            ItemStack output = tile.getOutputItem();
            if (!tile.getOutputItem().isEmpty() && stackInHand.is(CBItems.EMPTY_SACK.get())) {
                ItemStack stack = output.split(1);
                stackInHand.shrink(1);
                if (!player.addItem(stack)) {
                    player.drop(stack, false);
                }
                return InteractionResult.SUCCESS;
            }
            if (player.isShiftKeyDown()) {
                if (!input.isEmpty()) {
                    ItemStack stack = input.pop();
                    if (!player.addItem(stack)) {
                        player.drop(stack, false);
                    }
                    return InteractionResult.SUCCESS;
                }
            }else {
                if (stackInHand.isEmpty()) {
                    if (!tile.process(level, tile)) {
                        return InteractionResult.FAIL;
                    }
                    return InteractionResult.SUCCESS;
                }
                if (input.size() < 3) {
                    tile.addInputItem(stackInHand.split(1));
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.FAIL;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof MillingStoneBlockEntity tile) {
                List<ItemStack> list = tile.getInputItems();
                Collections.addAll(list, tile.getOutputItem());
                ItemUtils.dropItems(level, pos, list);
            }
            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MillingStoneBlockEntity(pos, state);
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return MillingStoneBlock.SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(LIT, true);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIT, FACING);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public boolean canBeReplaced(BlockState state, Fluid fluid) {
        return false;
    }
}
