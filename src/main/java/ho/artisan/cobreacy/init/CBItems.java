package ho.artisan.cobreacy.init;

import ho.artisan.cobreacy.Cobreacy;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class CBItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Cobreacy.MODID);

    //Bread
    public static final RegistryObject<Item> BAGEL;
    public static final RegistryObject<Item> BLAZE_BREAD;
    public static final RegistryObject<Item> BRIOCHE;
    public static final RegistryObject<Item> CREAM_BREAD;
    public static final RegistryObject<Item> JELLYFISH_DINNER_ROLL;
    public static final RegistryObject<Item> NETHER_WART_BREAD;
    public static final RegistryObject<Item> SLIME_BREAD;
    public static final RegistryObject<Item> TIGER_BLOOMER;
    public static final RegistryObject<Item> TOAST;

    //Materials
    public static final RegistryObject<Item> BLAZE_POWDER_BLANK;
    public static final RegistryObject<Item> FLOUR;
    public static final RegistryObject<Item> FLOUR_BREAD_BLANK;
    public static final RegistryObject<Item> NETHER_WART_BLANK;
    public static final RegistryObject<Item> SLIME_BLANK;

    public static final RegistryObject<Item> BLAZE_POWDER_SACK;
    public static final RegistryObject<Item> NETHER_WART_POWDER_SACK;
    public static final RegistryObject<Item> SLIME_SACK;

    //Misc
    public static final RegistryObject<Item> CREBLANK_FLUID_BUCKET;
    public static final RegistryObject<Item> EMPTY_SACK;

    static {
        BAGEL = register("bagel", new Item.Properties().food(CBFoodProperties.BAGEL));
        BLAZE_BREAD = register("blaze_bread", new Item.Properties().food(CBFoodProperties.BLAZE_BREAD));
        BRIOCHE = register("brioche", new Item.Properties().food(CBFoodProperties.BRIOCHE));
        CREAM_BREAD = register("cream_bread", new Item.Properties().food(CBFoodProperties.CREAM_BREAD));
        JELLYFISH_DINNER_ROLL = register("jellyfish_dinner_roll", new Item.Properties().food(CBFoodProperties.JELLYFISH_DINNER_ROLL));
        NETHER_WART_BREAD = register("nether_wart_bread", new Item.Properties().food(CBFoodProperties.NETHER_WART_BREAD));
        SLIME_BREAD = register("slime_bread", new Item.Properties().food(CBFoodProperties.SLIME_BREAD));
        TIGER_BLOOMER = register("tiger_bloomer", new Item.Properties().food(CBFoodProperties.TIGER_BLOOMER));
        TOAST = register("toast", new Item.Properties().food(CBFoodProperties.TOAST));

        BLAZE_POWDER_BLANK = register("blaze_powder_blank", new Item.Properties().stacksTo(32));
        FLOUR = register("flour", new Item.Properties().stacksTo(1));
        FLOUR_BREAD_BLANK = register("flour_bread_blank", new Item.Properties().stacksTo(32));
        NETHER_WART_BLANK = register("nether_wart_blank", new Item.Properties().stacksTo(32));
        SLIME_BLANK = register("slime_blank", new Item.Properties().stacksTo(32));
        BLAZE_POWDER_SACK = register("blaze_powder_sack", new Item.Properties().stacksTo(1));
        NETHER_WART_POWDER_SACK = register("nether_wart_powdwe_sack", new Item.Properties().stacksTo(1));
        SLIME_SACK = register("slime_sack", new Item.Properties().stacksTo(1));

        CREBLANK_FLUID_BUCKET = register("creblank_fluid_bucket", new BucketItem(CBFluids.CREBLANK_FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
        EMPTY_SACK = register("empty_sack", new Item.Properties().stacksTo(1));
    }

    public static RegistryObject<Item> register(String id, Item.Properties properties) {
        var a = ITEMS.register(id, () -> new Item(properties));
        return a;
    }

    public static RegistryObject<Item> register(String id, Item item) {
        var a = ITEMS.register(id, () -> item);
        return a;
    }
}
