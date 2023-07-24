package ho.artisan.cobreacy.api.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import ho.artisan.cobreacy.api.block.entity.MillingStoneBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.items.ItemStackHandler;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

/**
 * @author Goulixiaoji
 */
public class MillingStoneLayer extends GeoRenderLayer<MillingStoneBlockEntity> {
    private static final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
    public MillingStoneLayer(GeoRenderer<MillingStoneBlockEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, MillingStoneBlockEntity millingStoneBlockEntity, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemStackHandler inventory = millingStoneBlockEntity.getInventory();
        for (int g=0; g<inventory.getSlots(); g++) {
            if (!inventory.getStackInSlot(g).isEmpty()) {
                poseStack.pushPose();

                poseStack.translate(0.5D, 0.29D, 0.5D);

                float f = -0;
                poseStack.mulPose(Axis.YP.rotationDegrees(f));

                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

                Vec2 pos = millingStoneBlockEntity.getItemPos(g);
                poseStack.translate(pos.x, pos.y, 0.0D);

                poseStack.scale(0.375F, 0.375F, 0.375F);

                if (millingStoneBlockEntity.getLevel() != null) {
                    itemRenderer.renderStatic(inventory.getStackInSlot(g), ItemDisplayContext.FIXED, LevelRenderer.getLightColor(millingStoneBlockEntity.getLevel(), millingStoneBlockEntity.getBlockPos().above()), packedOverlay, poseStack, bufferSource, millingStoneBlockEntity.getLevel(), (int) millingStoneBlockEntity.getBlockPos().asLong());
                }
                poseStack.popPose();
            }
        }
    }
}
