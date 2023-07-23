package ho.artisan.cobreacy.api.block;

import ho.artisan.cobreacy.api.block.entity.MillingStoneBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

/**
 * @author Goulixiaoji
 */
public class MillingStoneBlock extends BaseEntityBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    protected MillingStoneBlock(Properties arg) {
        super(arg);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stackInHand = player.getItemInHand(hand);
        Item itemInHand = stackInHand.getItem();

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof MillingStoneBlockEntity millingStoneBlockEntity) {
            int slot = millingStoneBlockEntity.getNextEmptySlot();
            if (slot < 0) {
                return InteractionResult.PASS;
            }
            if (millingStoneBlockEntity.addItem(player.getAbilities().instabuild ? stackInHand.copy() : stackInHand, slot)) {
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MillingStoneBlockEntity(pos, state);
    }
}
