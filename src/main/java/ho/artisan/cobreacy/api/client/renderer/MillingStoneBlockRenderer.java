package ho.artisan.cobreacy.api.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import ho.artisan.cobreacy.api.block.entity.MillingStoneBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author Goulixiaoji
 */
public class MillingStoneBlockRenderer implements BlockEntityRenderer<MillingStoneBlockEntity> {

    public MillingStoneBlockRenderer(BlockEntityRendererProvider.Context context) { }

    @Override
    public void render(MillingStoneBlockEntity millingStoneBlockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        ItemStackHandler inventory = millingStoneBlockEntity.getInventory();
        for (int g=0; g<inventory.getSlots(); g++) {
            if (!inventory.getStackInSlot(g).isEmpty()) {
                poseStack.pushPose();

                poseStack.translate(0.5D, 1.02D, 0.5D);

                float f = -0;
                poseStack.mulPose(Axis.YP.rotationDegrees(f));

                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

                Vec2 pos = millingStoneBlockEntity.getItemPos(g);
                poseStack.translate(pos.x, pos.y, 0.0D);

                poseStack.scale(0.375F, 0.375F, 0.375F);

                if (millingStoneBlockEntity.getLevel() != null) {
                    Minecraft.getInstance().getItemRenderer().renderStatic(inventory.getStackInSlot(g), ItemDisplayContext.FIXED, LevelRenderer.getLightColor(millingStoneBlockEntity.getLevel(), millingStoneBlockEntity.getBlockPos().above()), packedOverlay, poseStack, buffer, millingStoneBlockEntity.getLevel(), (int) millingStoneBlockEntity.getBlockPos().asLong());
                }
                poseStack.popPose();
            }
        }
    }
}
