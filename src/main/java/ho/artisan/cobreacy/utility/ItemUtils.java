package ho.artisan.cobreacy.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemUtils {
    public static void dropItems(Level level, BlockPos pos, List<ItemStack> list) {
        for (ItemStack stack : list) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), stack);
        }
    }
}
