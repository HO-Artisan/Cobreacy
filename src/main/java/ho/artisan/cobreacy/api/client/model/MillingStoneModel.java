package ho.artisan.cobreacy.api.client.model;

import ho.artisan.cobreacy.Cobreacy;
import ho.artisan.cobreacy.api.block.entity.MillingStoneBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

/**
 * @author Goulixiaoji
 */
public class MillingStoneModel extends DefaultedBlockGeoModel<MillingStoneBlockEntity> {

    public MillingStoneModel() {
        super(new ResourceLocation(Cobreacy.MODID, "milling_stone"));
    }

    @Override
    public RenderType getRenderType(MillingStoneBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}
