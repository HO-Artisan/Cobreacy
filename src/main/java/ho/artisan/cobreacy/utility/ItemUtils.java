package ho.artisan.cobreacy.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;

public class ItemUtils {
    public static void dropItems(Level level, BlockPos pos, IItemHandler inventory) {
        for (int slot = 0; slot < inventory.getSlots(); slot++) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
        }
    }
}
