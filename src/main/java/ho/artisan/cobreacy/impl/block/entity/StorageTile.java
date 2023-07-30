package ho.artisan.cobreacy.impl.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class StorageTile extends BaseBlockEntity implements Container {
    protected NonNullList<ItemStack> list;

    public StorageTile(BlockEntityType<? extends StorageTile> type, BlockPos pos, BlockState state, int size) {
        super(type, pos, state);
        list = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    @Override
    public int getContainerSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        int k = 0;
        for (ItemStack stack : list) {
            if (stack.isEmpty())
                k++;
        }
        return list.isEmpty() || k == 3;
    }

    @Override
    public ItemStack getItem(int id) {
        return list.get(id);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(list, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int id) {
        return ContainerHelper.takeItem(list, id);
    }

    @Override
    public void setItem(int id, ItemStack stack) {
        this.list.set(id, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        list.clear();
    }
}
