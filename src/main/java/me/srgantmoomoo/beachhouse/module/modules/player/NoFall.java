package me.srgantmoomoo.beachhouse.module.modules.player;

public class NoFall extends me.srgantmoomoo.bedroom.module.Module {

	public NoFall() {
		super("no fall", "nofall", "prevents u from taking fall damage.", 0, Category.PLAYER);
	}
	
	/*@Override
	public void onEvent(Event e) {
		if(e instaceof EventPacket)
			if (event.getPacket() instanceof CPacketPlayer) {
				final CPacketPlayer packet = (CPacketPlayer) event.getPacket();
				if (event.getPacket() instanceof CPacketPlayer && Minecraft.getMinecraft().player.fallDistance >= 3.0f) {
					packet.onGround = true;
				}
			}
	}*/

}
