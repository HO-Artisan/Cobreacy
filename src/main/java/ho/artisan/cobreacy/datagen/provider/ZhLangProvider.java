package ho.artisan.cobreacy.datagen.provider;

import ho.artisan.cobreacy.Cobreacy;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ZhLangProvider extends LanguageProvider {
    public ZhLangProvider(PackOutput output) {
        super(output, Cobreacy.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {

    }
}
