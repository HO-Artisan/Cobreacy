package ho.artisan.cobreacy.init;

import ho.artisan.cobreacy.Cobreacy;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CBItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Cobreacy.MODID);

    public static final RegistryObject<Item> FERMENTATION_FLUID_BUCKET;

    static {
        FERMENTATION_FLUID_BUCKET = ITEMS.register("fermentation_fluid_bucket", () -> new BucketItem(CBFluids.FERMENTATION_FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    }
}
