package com.dezc.labycheck.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;
import net.labymod.main.LabyMod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;

import java.awt.*;

public class RenderEvent {

    public static String player = "";
    public static boolean onCheck = false;
    public static long onTimeCheck = 0;
    public static int xCoords = 10;
    public static int yCoords = 10;
    @Subscribe
    public void onRender(RenderGameOverlayEvent event) {

        if (onCheck) {
            Minecraft.getInstance().fontRenderer.drawStringWithShadow(event.getMatrixStack(), "Текущая проверка:", xCoords, yCoords, rainbow(300));
            Minecraft.getInstance().fontRenderer.drawStringWithShadow(event.getMatrixStack(), player + " | " + ((System.currentTimeMillis() / 1000L - onTimeCheck)/60) + ":" + ((System.currentTimeMillis() / 1000L - onTimeCheck)%60), xCoords + 10, yCoords + 10, rainbow(300));
        }
    }
    public static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 0.5f, 1f).getRGB();
    }

    public static void setOnCheck(boolean onCheckIn) {
        onCheck = onCheckIn;
    }
    public static void setPlayer(String playerIn) {
        player = playerIn;
    }

    public static void setOnTimeCheck(long onTimeCheckIn) {
        onTimeCheck = onTimeCheckIn;
    }

    public static String getPlayer() {
        return player;
    }
    public static boolean getCheck() {
        return onCheck;
    }
    public static void setxCoords(int xIn) {
        xCoords = xIn;
    }

    public static int getxCoords() {
        return xCoords;
    }

    public static void setyCoords(int yIn) {
        yCoords = yIn;
    }

    public static int getyCoords() {
        return yCoords;
    }
}
