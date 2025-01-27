package superlord.prehistoricfauna.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import superlord.prehistoricfauna.PrehistoricFauna;
import superlord.prehistoricfauna.client.model.HesperornithoidesModel;
import superlord.prehistoricfauna.common.entities.HesperornithoidesEntity;

public class HesperornithoidesRenderer extends MobRenderer<HesperornithoidesEntity, EntityModel<HesperornithoidesEntity>> {

	private static final ResourceLocation HESPERORNITHOIDES = new ResourceLocation(PrehistoricFauna.MOD_ID, "textures/entities/hesperornithoides/hesperornithoides.png");
	private static final ResourceLocation ALBINO = new ResourceLocation(PrehistoricFauna.MOD_ID, "textures/entities/hesperornithoides/albino.png");
	private static final ResourceLocation MELANISTIC = new ResourceLocation(PrehistoricFauna.MOD_ID, "textures/entities/hesperornithoides/melanistic.png");
	private static final HesperornithoidesModel HESPERORNITHOIDES_MODEL = new HesperornithoidesModel();
	
	public HesperornithoidesRenderer() {
		super(Minecraft.getInstance().getRenderManager(), HESPERORNITHOIDES_MODEL, 0.375F);
	}
	
	protected void preRenderCallback(HesperornithoidesEntity entity, MatrixStack matrixStackIn, float partialTickTime) {
		if(entity.isChild()) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		}
	}
	
	@Override
	public ResourceLocation getEntityTexture(HesperornithoidesEntity entity) {
		if (entity.isAlbino()) {
			return ALBINO;
		} else if (entity.isMelanistic()) {
			return MELANISTIC;
		} else {
			return HESPERORNITHOIDES;
		}
	}
	
}
