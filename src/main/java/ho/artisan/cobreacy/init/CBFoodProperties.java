package ho.artisan.cobreacy.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class CBFoodProperties {
    public static final FoodProperties BAGEL = new FoodProperties.Builder().nutrition(2).saturationMod(2).build();
    public static final FoodProperties BLAZE_BREAD = new FoodProperties.Builder().nutrition(5).saturationMod(4).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 1), 1.0F).build();
    public static final FoodProperties CREAM_BREAD = new FoodProperties.Builder().nutrition(6).saturationMod(8).build();
    public static final FoodProperties JELLYFISH_DINNER_ROLL = new FoodProperties.Builder().nutrition(6).saturationMod(6).build();
    public static final FoodProperties NETHER_WART_BREAD = new FoodProperties.Builder().nutrition(10).saturationMod(1).build();
    public static final FoodProperties SLIME_BREAD = new FoodProperties.Builder().nutrition(2).saturationMod(10).effect(() -> new MobEffectInstance(MobEffects.JUMP, 1200, 1), 1.0F).build();
    public static final FoodProperties TIGER_BLOOMER = new FoodProperties.Builder().nutrition(6).saturationMod(6).build();
    public static final FoodProperties TOAST = new FoodProperties.Builder().nutrition(6).saturationMod(5).build();
}
