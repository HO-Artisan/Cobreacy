package ho.artisan.cobreacy.api.block.entity;

import ho.artisan.cobreacy.init.CBAnimations;
import ho.artisan.cobreacy.init.CBBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.items.ItemStackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * @author Goulixiaoji
 */
public class MillingStoneBlockEntity extends SyncedBlockEntity implements GeoBlockEntity {

    public static final int INVENTORY_HANDLER_COUNT = 3;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean onUse = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(MillingStoneBlockEntity.class);

    private final ItemStackHandler inventory;
    public MillingStoneBlockEntity(BlockPos pos, BlockState state) {
        super(CBBlockEntityTypes.MILLING_STONE.get(), pos, state);
        this.inventory = createInventory();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        writeItems(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Inventory")) {
            inventory.deserializeNBT(tag.getCompound("Inventory"));
        } else {
            inventory.deserializeNBT(tag);
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        return writeItems(new CompoundTag());
    }

    private CompoundTag writeItems(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", inventory.serializeNBT());
        return tag;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public boolean addItem(ItemStack stackIn, int slot) {
        if (0 <= slot && slot < inventory.getSlots()) {
            ItemStack slotStack = inventory.getStackInSlot(slot);
            if (slotStack.isEmpty()) {
                inventory.setStackInSlot(slot, stackIn.split(1));
                inventoryChanged();
                return true;
            }
        }
        return false;
    }

    public int getNextEmptySlot() {
        for (int i = 0; i < inventory.getSlots(); ++i) {
            ItemStack slotStack = inventory.getStackInSlot(i);
            if (slotStack.isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    private ItemStackHandler createInventory() {
        return new ItemStackHandler(MillingStoneBlockEntity.INVENTORY_HANDLER_COUNT)
        {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        };
    }

    public Vec2 getItemPos(int slot) {
        return switch (slot) {
            case 0 -> new Vec2(0.0F, 0.3F);
            case 1 -> new Vec2(0.28F, -0.3F);
            case 2 -> new Vec2(-0.28F, -0.3F);
            default -> new Vec2(0.0F, 0.0F);
        };
    }

    public void setOnUse() {
        this.onUse = true;
    }

    public boolean isOnUse() {
        return this.onUse;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controller) {
            controller.add(new AnimationController<>(this, (state) -> {
                if (this.onUse) {
                    LOGGER.info("running!");
                    state.setAnimation(CBAnimations.MILLING_STONE_RUNNING);
                }

                if (!state.isMoving()) {
                    onUse = false;
                    state.resetCurrentAnimation();
                }
                else {
                    return PlayState.CONTINUE;
                }

                return PlayState.STOP;
            }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
