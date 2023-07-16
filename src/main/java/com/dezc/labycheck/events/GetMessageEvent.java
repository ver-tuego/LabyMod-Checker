package com.dezc.labycheck.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class GetMessageEvent {

    public static String message = "";
    public static boolean onCopy = false;

    public static String vkUrl = "";

    @Subscribe
    public void getMessages(MessageReceiveEvent event) {
        if (!event.getComponent().getString().equals(message)) {
            message = event.getComponent().getString();

            if (event.getComponent().getString().startsWith("[" + RenderEvent.getPlayer() + " ->")) {
                String result = "";

                for (int i = 3; i < event.getComponent().getString().split(" ").length; i++) {
                    result += event.getComponent().getString().split(" ")[i];
                }
                StringSelection stringSelection = new StringSelection(result);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
            if (event.getComponent().getString().startsWith("▶ Замороженный игрок " + RenderEvent.getPlayer())) {
                Minecraft.getInstance().player.sendChatMessage("/tempban " + RenderEvent.getPlayer() + " 30d 2.4 ( Лив с проверки ) | Вопросы? " + vkUrl);
                Minecraft.getInstance().player.sendChatMessage("/freezing " + RenderEvent.getPlayer());
                RenderEvent.setPlayer("");
                RenderEvent.setOnCheck(false);
            }
        }
    }

    public static void setVkUrl(String vkUrlIn) {
        vkUrl = vkUrlIn;
    }

    public static String getVkUrl() {
        return vkUrl;
    }

}
