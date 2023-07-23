package ho.artisan.cobreacy.init;

import ho.artisan.cobreacy.Cobreacy;
import ho.artisan.cobreacy.api.block.entity.BrickOvensBlockEntity;
import ho.artisan.cobreacy.api.block.entity.MillingStoneBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CBBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Cobreacy.MODID);

    public static final RegistryObject<BlockEntityType<BrickOvensBlockEntity>> BRICK_OVENS;
    public static final RegistryObject<BlockEntityType<MillingStoneBlockEntity>> MILLING_STONE;

    static {
        BRICK_OVENS = BLOCK_ENTITY_TYPE.register("brick_ovens", () -> BlockEntityType.Builder.of(BrickOvensBlockEntity::new, Blocks.AIR).build(null));
        MILLING_STONE = BLOCK_ENTITY_TYPE.register("milling_stone", () -> BlockEntityType.Builder.of(MillingStoneBlockEntity::new, CBBlocks.MILLING_STONE.get()).build(null));
    }

}
