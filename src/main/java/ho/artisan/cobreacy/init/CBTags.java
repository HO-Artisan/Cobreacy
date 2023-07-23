package ho.artisan.cobreacy.init;

import ho.artisan.cobreacy.Cobreacy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CBTags {

    public static final TagKey<Item> FLOORS = ItemTags.create(new ResourceLocation(Cobreacy.MODID, "floors"));

}
