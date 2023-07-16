package ho.artisan.cobreacy.datagen;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CobreacyDataGen {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        //event.getGenerator().addProvider();
    }
}
