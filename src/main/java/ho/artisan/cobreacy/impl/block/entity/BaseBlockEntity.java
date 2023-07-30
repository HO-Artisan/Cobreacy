package ho.artisan.cobreacy.impl.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class BaseBlockEntity extends BlockEntity {

    public BaseBlockEntity(BlockEntityType<?> arg, BlockPos arg2, BlockState arg3) {
        super(arg, arg2, arg3);
    }

    public abstract void read(CompoundTag nbt, boolean isClient);

    public abstract void write(CompoundTag nbt, boolean isClient);

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.read(pkt.getTag(), true);
    }

    @Override
    public void load(CompoundTag nbt) {
        this.read(nbt, false);
        super.load(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        this.write(compound, false);
        super.saveAdditional(compound);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        write(nbt, true);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        CompoundTag nbt = new CompoundTag();
        write(nbt, true);
        return ClientboundBlockEntityDataPacket.create(this, i -> nbt);
    }
}
