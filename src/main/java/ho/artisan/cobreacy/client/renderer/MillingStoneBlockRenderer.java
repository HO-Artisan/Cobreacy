package ho.artisan.cobreacy.client.renderer;

import ho.artisan.cobreacy.impl.block.entity.MillingStoneBlockEntity;
import ho.artisan.cobreacy.client.layer.MillingStoneLayer;
import ho.artisan.cobreacy.client.model.MillingStoneModel;
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
