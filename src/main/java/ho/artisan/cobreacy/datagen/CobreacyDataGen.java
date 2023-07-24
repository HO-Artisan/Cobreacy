package ho.artisan.cobreacy.datagen;

import ho.artisan.cobreacy.Cobreacy;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CobreacyDataGen {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        var helper = event.getExistingFileHelper();
        //gen.addProvider(event.includeClient(), new EnglishLanguageProvider(packOutput));
        //gen.addProvider(event.includeClient(), new ChineseLanguageProvider(packOutput));
    }

    public static class EnglishLanguageProvider extends LanguageProvider {
        public EnglishLanguageProvider(PackOutput output) {
            super(output, Cobreacy.MODID, "en_us");
        }

        @Override
        protected void addTranslations() {

        }
    }

    public static class ChineseLanguageProvider extends LanguageProvider {
        public ChineseLanguageProvider(PackOutput output) {
            super(output, Cobreacy.MODID, "zh_cn");
        }

        @Override
        protected void addTranslations() {

        }
    }
}
