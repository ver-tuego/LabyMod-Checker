package com.dezc.labycheck.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import net.minecraft.client.Minecraft;

import java.util.Objects;

public class FreezerEvent {

    public static boolean enableDupeIp = false;

    @Subscribe
    public void onUpdate(MessageSendEvent event) {
        if (event.getMessage().startsWith("/freezing")) {
            String[] array = event.getMessage().split(" ");
            event.setCancelled(true);
            Minecraft mc = Minecraft.getInstance();

            if (RenderEvent.getCheck()) {
                if (Objects.equals(array[1], RenderEvent.getPlayer())) {
                    mc.player.sendChatMessage("/freezing " + array[1]);
                    RenderEvent.setOnCheck(false);
                    RenderEvent.setPlayer("");
                } else {
                    mc.player.sendChatMessage("/freezing " + array[1]);
                }
            } else {
                mc.player.sendChatMessage("/freezing " + array[1]);
                mc.player.sendChatMessage("/w " + array[1] + " &c&lЭто проверка на читы, &eу Вас есть 7 минут, чтобы скинуть Ваш ID Анидеска &c&lAnyDesk &f(anydesk,com) &eи пройти проверку. В случае отказа/выхода/игнора - блокировка аккаунта");
                mc.player.sendChatMessage("/w " + array[1] + " &c&lОбратите внимание! &eМы не собираемся причинить вред Вашему компьютеру, удаленный доступ используется исключительно для удобства проверки. В любой момент Вы можете закрыть соединение, но если сотрудник прав - бан.");
                mc.player.sendChatMessage("/checkmute " + array[1]);
                if (enableDupeIp) {
                    mc.player.sendChatMessage("/dupeip " + array[1]);
                }
                RenderEvent.setOnTimeCheck(System.currentTimeMillis() / 1000L);
                RenderEvent.setOnCheck(true);
                RenderEvent.setPlayer(array[1]);
            }
        }
        if (event.getMessage().startsWith("/frz")) {
            String[] array = event.getMessage().split(" ");
            event.setCancelled(true);
            Minecraft.getInstance().player.sendChatMessage("/freezing " + array[1]);
        }
    }

    public static boolean isEnableDupeIp() {
        return enableDupeIp;
    }

    public static void setEnableDupeIp(boolean enableDupeIp) {
        FreezerEvent.enableDupeIp = enableDupeIp;
    }
}
