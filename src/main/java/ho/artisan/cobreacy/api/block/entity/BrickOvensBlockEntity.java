package ho.artisan.cobreacy.api.block.entity;

import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static ho.artisan.cobreacy.init.CBBlockEntityTypes.BRICK_OVENS;

public class BrickOvensBlockEntity extends BlockEntity {
    public BrickOvensBlockEntity(BlockPos arg2, BlockState arg3) {
        super(BRICK_OVENS.get(), arg2, arg3);
    }

}
