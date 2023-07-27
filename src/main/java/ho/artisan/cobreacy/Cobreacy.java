package ho.artisan.cobreacy;

import com.mojang.logging.LogUtils;
import ho.artisan.cobreacy.init.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(Cobreacy.MODID)
public class Cobreacy {
    public static final String MODID = "cobreacy";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Cobreacy() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        CBItems.ITEMS.register(bus);
        CBBlocks.BLOCKS.register(bus);
        CBTabs.TABS.register(bus);
        CBFluids.FLUIDS.register(bus);
        CBFluids.FLUID_TYPES.register(bus);
        CBBlockEntityTypes.BLOCK_ENTITY_TYPE.register(bus);
        CBRecipeTypes.RECIPE_TYPES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
