package ho.artisan.cobreacy.datagen.provider;

import ho.artisan.cobreacy.Cobreacy;
import ho.artisan.cobreacy.index.CBItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Arrays;

public class EnLangProvider extends LanguageProvider {
    public EnLangProvider(PackOutput output) {
        super(output, Cobreacy.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        CBItems.ITEMS.getEntries().forEach((item -> {
            add(item.get(), capital(item.getId().getPath()));
        }));
    }

    private String capital(String str) {
        StringBuilder sb = new StringBuilder(str.replace("_", " "));
        sb.insert(0, " ");
        for (int i = 1; i < sb.length(); i++) {
            if(sb.substring(i-1, i).equals(" ")) {
                String []arr= {sb.substring(i,i+1)};
                arr[0] += -32;
                sb.replace(i, i+1, Arrays.toString(arr));
            }
        }
        return sb.toString();
    }
}
