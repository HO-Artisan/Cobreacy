package ho.artisan.cobreacy.api.client.renderer;

import ho.artisan.cobreacy.api.block.entity.MillingStoneBlockEntity;
import ho.artisan.cobreacy.api.client.layer.MillingStoneLayer;
import ho.artisan.cobreacy.api.client.model.MillingStoneModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

/**
 * @author Goulixiaoji
 */
public class MillingStoneBlockRenderer extends GeoBlockRenderer<MillingStoneBlockEntity> {

    public MillingStoneBlockRenderer() {
        super(new MillingStoneModel());
        addRenderLayer(new MillingStoneLayer(this));
    }
}
