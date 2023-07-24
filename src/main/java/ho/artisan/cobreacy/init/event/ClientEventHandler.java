package ho.artisan.cobreacy.init.event;

import ho.artisan.cobreacy.api.client.renderer.MillingStoneBlockRenderer;
import ho.artisan.cobreacy.init.CBBlockEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author Goulixiaoji
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(CBBlockEntityTypes.MILLING_STONE.get(), MillingStoneBlockRenderer::new);
    }
}
