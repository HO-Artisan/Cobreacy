package ho.artisan.cobreacy.impl.block.entity;

import ho.artisan.cobreacy.impl.recipe.MillingStoneRecipe;
import ho.artisan.cobreacy.init.CBAnimations;
import ho.artisan.cobreacy.init.CBBlockEntityTypes;
import ho.artisan.cobreacy.init.CBRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;
import java.util.Stack;

/**
 * @author Goulixiaoji
 */
public class MillingStoneBlockEntity extends BaseBlockEntity implements GeoBlockEntity, Container {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected final RecipeType<MillingStoneRecipe> recipeType = CBRecipeTypes.MILLING_STONE.get();
    protected NonNullList<ItemStack> list = NonNullList.withSize(1, ItemStack.EMPTY);
    private Stack<ItemStack> stack = new Stack<>();
    private int processtime = 0;

    public MillingStoneBlockEntity(BlockPos pos, BlockState state) {
        super(CBBlockEntityTypes.MILLING_STONE.get(), pos, state);
    }

    @Override
    public void read(CompoundTag nbt, boolean isClient) {
        if (!isClient) {
            this.list.set(0, ItemStack.of(nbt.getCompound("ResultItem")));

            Stack<ItemStack> vStack = new Stack<>();
            for (Tag tag : nbt.getList("Inventory", nbt.getId())) {
                vStack.add(ItemStack.of((CompoundTag) tag));
            }
            this.stack = vStack;

            this.processtime = nbt.getInt("ProcessTime");
        }
    }

    @Override
    public void write(CompoundTag nbt, boolean isClient) {
        if (!isClient) {
            nbt.put("ResultItem", this.list.get(0).serializeNBT());

            ListTag listTag = new ListTag();
            for (ItemStack stack : this.stack) {
                listTag.add(stack.serializeNBT());
            }
            nbt.put("Inventory", listTag);

            nbt.putInt("ProcessTime", this.processtime);
        }
    }

    public Stack<ItemStack> getInputItems() {
        return stack;
    }

    public ItemStack getOutputItem() {
        return this.list.get(0);
    }

    public void addInputItem(ItemStack stack) {
        this.stack.add(stack);
    }

    public ItemStack popInputItem() {
        return this.stack.pop();
    }

    public void setOutputItem(ItemStack stack) {
        this.list.set(0, stack);
    }

    public boolean isOnUse() {
        return this.processtime > 0 && this.processtime != 40;
    }

    public boolean process(Level level, MillingStoneBlockEntity tile) {
        Optional<MillingStoneRecipe> optional = level.getRecipeManager().getRecipeFor(tile.recipeType, tile, level);
        if (optional.isPresent()) {
            if (this.processtime < 40) {
                this.processtime++;
                return true;
            } else if (this.processtime == 40) {
                ItemStack inner = tile.getOutputItem();
                MillingStoneRecipe recipe = optional.get();
                ItemStack result = recipe.getResultItem(level.registryAccess());
                if (inner.isEmpty()) {
                    tile.setOutputItem(result.copy());
                    tile.setChanged();
                    tile.clearContent();
                    this.processtime = 0;
                    return true;
                }
            }
        }
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public Vec2 getItemPos(int slot) {
        return switch (slot) {
            case 0 -> new Vec2(0.0F, 0.3F);
            case 1 -> new Vec2(0.28F, -0.3F);
            case 2 -> new Vec2(-0.28F, -0.3F);
            default -> new Vec2(0.0F, 0.0F);
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controller) {
        controller.add(new AnimationController<>(this, (state) -> {
            if (isOnUse()) {
                state.setAnimation(CBAnimations.MILLING_STONE_RUNNING);
//                if (it's still a dream) {
//                    Fate
//                }
                return PlayState.CONTINUE;
            }
            return PlayState.STOP;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public int getContainerSize() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return stack.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(list, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(list, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.stack.set(slot, stack);
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
        stack.clear();
    }
}
