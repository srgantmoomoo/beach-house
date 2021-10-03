package me.srgantmoomoo.beachhouse.feature.module.modules.player;

import me.srgantmoomoo.beachhouse.backend.events.EventBlockShape;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventTick;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShapes;

public class Jesus extends Module {
	public BooleanSetting velocity = new BooleanSetting("velocity", this, true);
	
	public Jesus() {
		super("jesus", "jesus", "godd.", 0, Category.PLAYER);
		this.addSettings(velocity);
	}

	@Override
	public void onEvent(Event e) {

		if(e instanceof EventBlockShape) {
			if(minecraft.player == null || minecraft.world == null)
				return;

			if (isBlockFluid(((EventBlockShape) e).getPos())
					&& !minecraft.player.isSneaking()
					&& !minecraft.player.isTouchingWater()
					&& minecraft.player.getY() >= ((EventBlockShape) e).getPos().getY() + 0.9) {
				((EventBlockShape) e).setShape(VoxelShapes.fullCube());
			}
		}

		if(e instanceof EventTick) {
			if(minecraft.player == null || minecraft.world == null)
				return;

			if(velocity.isEnabled()) {
				Entity entity = minecraft.player;

				if (entity.isSneaking() || entity.fallDistance > 3f)
					return;

				if(isBlockFluid(new BlockPos(entity.getPos().add(0, 0.3, 0))))
					entity.setVelocity(entity.getVelocity().getX(), 0.08, entity.getVelocity().getZ());

				else if(isBlockFluid(new BlockPos(entity.getPos().add(0, 0.1, 0))))
					entity.setVelocity(entity.getVelocity().getX(), 0.05, entity.getVelocity().getZ());

				else if (isBlockFluid(new BlockPos(entity.getPos().add(0, 0.05, 0))))
					entity.setVelocity(entity.getVelocity().getX(), 0.01, entity.getVelocity().getZ());

				else if (isBlockFluid(entity.getBlockPos()))
					entity.setVelocity(entity.getVelocity().getX(), 0.01, entity.getVelocity().getZ());
			}
		}
	}

	private boolean isBlockFluid(BlockPos pos) {
		assert minecraft.world != null;
		Material currentMaterial = minecraft.world.getBlockState(pos).getMaterial();
		Material water = Material.WATER;
		Material lava = Material.LAVA;

		return currentMaterial == water || currentMaterial == lava;
	}

}
