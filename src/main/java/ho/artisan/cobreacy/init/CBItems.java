package ho.artisan.cobreacy.init;

import ho.artisan.cobreacy.Cobreacy;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CBItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Cobreacy.MODID);

    // Bread
    public static final RegistryObject<Item> BAGEL;
    public static final RegistryObject<Item> BLAZE_BREAD;
    public static final RegistryObject<Item> CREAM_BREAD;
    public static final RegistryObject<Item> JELLYFISH_DINNER_ROLL;
    public static final RegistryObject<Item> NETHER_WART_BREAD;
    public static final RegistryObject<Item> SLIME_BREAD;
    public static final RegistryObject<Item> TIGER_BLOOMER;
    public static final RegistryObject<Item> TOAST;

    // Materials
    public static final RegistryObject<Item> BLAZE_POWDER_BLANK;
    public static final RegistryObject<Item> FLOUR;
    public static final RegistryObject<Item> FLOUR_BREAD_BLANK;
    public static final RegistryObject<Item> NETHER_WART_BLANK;
    public static final RegistryObject<Item> SLIME_BLANK;
    public static final RegistryObject<Item> TIGER_BLOOMER_BLANK;
    public static final RegistryObject<Item> JELLYFISH_DINNER_ROLL_BLANK;
    public static final RegistryObject<Item> BAGEL_BLANK;
    public static final RegistryObject<Item> CREAM_BREAD_BLANK;

    public static final RegistryObject<Item> BLAZE_POWDER_SACK;
    public static final RegistryObject<Item> NETHER_WART_POWDER_SACK;
    public static final RegistryObject<Item> SLIME_SACK;

    // Misc
    public static final RegistryObject<Item> CREBLANK_FLUID_BUCKET;
    public static final RegistryObject<Item> EMPTY_SACK;

    // Block
    public static final RegistryObject<BlockItem> MILLING_STONE;

    static {
        BAGEL = registerItem("bagel", new Item.Properties().food(CBFoodProperties.BAGEL));
        BLAZE_BREAD = registerItem("blaze_bread", new Item.Properties().food(CBFoodProperties.BLAZE_BREAD));
        CREAM_BREAD = registerItem("cream_bread", new Item.Properties().food(CBFoodProperties.CREAM_BREAD));
        JELLYFISH_DINNER_ROLL = registerItem("jellyfish_dinner_roll", new Item.Properties().food(CBFoodProperties.JELLYFISH_DINNER_ROLL));
        NETHER_WART_BREAD = registerItem("nether_wart_bread", new Item.Properties().food(CBFoodProperties.NETHER_WART_BREAD));
        SLIME_BREAD = registerItem("slime_bread", new Item.Properties().food(CBFoodProperties.SLIME_BREAD));
        TIGER_BLOOMER = registerItem("tiger_bloomer", new Item.Properties().food(CBFoodProperties.TIGER_BLOOMER));
        TOAST = registerItem("toast", new Item.Properties().food(CBFoodProperties.TOAST));

        BLAZE_POWDER_BLANK = registerItem("blaze_powder_blank", new Item.Properties().stacksTo(32));
        FLOUR = registerItem("flour", new Item.Properties().stacksTo(1));
        FLOUR_BREAD_BLANK = registerItem("flour_bread_blank", new Item.Properties().stacksTo(32));
        NETHER_WART_BLANK = registerItem("nether_wart_blank", new Item.Properties().stacksTo(32));
        SLIME_BLANK = registerItem("slime_blank", new Item.Properties().stacksTo(32));
        TIGER_BLOOMER_BLANK = registerItem("tiger_bloomer_blank", new Item.Properties().stacksTo(32));
        JELLYFISH_DINNER_ROLL_BLANK = registerItem("jellyfish_dinner_roll_blank", new Item.Properties().stacksTo(32));
        BAGEL_BLANK = registerItem("bagel_blank", new Item.Properties().stacksTo(32));
        CREAM_BREAD_BLANK = registerItem("cream_bread_blank", new Item.Properties().stacksTo(32));

        BLAZE_POWDER_SACK = registerItem("blaze_powder_sack", new Item.Properties().stacksTo(1));
        NETHER_WART_POWDER_SACK = registerItem("nether_wart_powder_sack", new Item.Properties().stacksTo(1));
        SLIME_SACK = registerItem("slime_sack", new Item.Properties().stacksTo(1));

        CREBLANK_FLUID_BUCKET = registerBucketItem("creblank_fluid_bucket", CBFluids.CREBLANK_FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
        EMPTY_SACK = registerItem("empty_sack", new Item.Properties().stacksTo(1));

        MILLING_STONE = registerBlockItem("milling_stone", CBBlocks.MILLING_STONE, new Item.Properties());
    }

    public static RegistryObject<Item> registerItem(String id, Item.Properties properties) {
        return ITEMS.register(id, () -> new Item(properties));
    }

    private static RegistryObject<Item> registerBucketItem(String id, Supplier<? extends Fluid> supplier, Item.Properties properties) {
        return ITEMS.register(id, () -> new Item(properties));
        //TODO, NO TIME :sad:
    }

    public static RegistryObject<BlockItem> registerBlockItem(String id, RegistryObject<Block> block, Item.Properties properties) {
        return ITEMS.register(id, () -> new BlockItem(block.get(), properties));
    }
}
