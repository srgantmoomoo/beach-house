package me.srgantmoomoo.beachhouse.backend.mixins;

import io.netty.channel.ChannelHandlerContext;
import me.srgantmoomoo.beachhouse.backend.events.EventRender3d;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.event.Type;
import me.srgantmoomoo.bedroom.event.events.EventPacket;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import me.srgantmoomoo.bedroom.command.CommandManager;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Shadow
    private Channel channel;

    @Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
    public void send(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> callback, CallbackInfo info) {
        EventPacket.Send e = new EventPacket.Send(packet);
        e.setType(Type.PRE);
        ModuleManager.onEvent(e);
        if (e.isCancelled()) info.cancel();
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    public void receive(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo info) {
        EventPacket.Receive e = new EventPacket.Receive(packet);
        e.setType(Type.PRE);
        ModuleManager.onEvent(e);
        if (e.isCancelled()) info.cancel();
    }

    @Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
    public void send_1(Packet<?> packet_1, GenericFutureListener<? extends Future<? super Void>> genericFutureListener_1, CallbackInfo info) {
        if (packet_1 instanceof ChatMessageC2SPacket) {
            ChatMessageC2SPacket pack = (ChatMessageC2SPacket) packet_1;
            if (pack.getChatMessage().startsWith(Bedroom.commandManager.prefix)) {
                Bedroom.commandManager.callCommandReturn(pack.getChatMessage());
                info.cancel();
            }
        }
    }

}
