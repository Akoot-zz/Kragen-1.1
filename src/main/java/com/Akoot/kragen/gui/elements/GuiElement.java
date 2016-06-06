package com.Akoot.kragen.gui.elements;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.Akoot.kragen.util.Colors;
import com.Akoot.kragen.util.Delta;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class GuiElement extends Gui
{
	public Minecraft mc;
	public static FontRenderer fr;
	
	public int x;
	public int y;
	public int width;
	public int height;

	public GuiElement(Minecraft mc)
	{
		this.mc = mc;
		this.fr = mc.fontRendererObj;
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		init();
	}
	
	public Delta getCenter()
	{
		return new Delta(getWidth() / 2, getHeight() / 2);
	}
	
	public void drawStringWithScale(String text, int x, int y, int color, double scale)
	{
		GL11.glPushMatrix();
		GL11.glScalef((float) (scale), (float) (scale), (float) (scale));
		x *= 1 + scale * (scale * 4);
		y *= 1 + scale * (scale * 4);
		fr.drawString(text, x, y, color);
		GL11.glPopMatrix();
	}
	
	public void drawCenteredStringWithScale(String text, int x, int y, int color, double scale)
	{
		drawStringWithScale(text, (int)(x - fr.getStringWidth(text) / 2), y, color, scale);
	}
	
	public int getWidth()
	{
		return new ScaledResolution(mc).getScaledWidth();
	}
	
	public int getHeight()
	{
		return new ScaledResolution(mc).getScaledHeight();
	}
	
	public Delta getMouse()
	{
		return new Delta((int) ((double) ((Mouse.getX()) / (double) (mc.displayWidth)) * getWidth()), (int) ((double) (((-(Mouse.getY() - mc.displayHeight))) / (double) (mc.displayHeight)) * getHeight()));
	}
	
	/**
	 * <i>Keeps text colors from messing up textures</i>
	 */
	public void fixColor()
	{
		GlStateManager.color(255, 255, 255, 255);
	}
	
	public String getUsername()
	{
		return mc.getSession().getUsername();
	}
	
	public void drawPlayerHead(int x, int y, int size, String username)
	{
		ResourceLocation skin  = AbstractClientPlayer.getLocationSkin(username);
		AbstractClientPlayer.getDownloadImageSkin(skin, username);
		mc.getTextureManager().bindTexture(skin);
		this.drawModalRectWithCustomSizedTexture(x, y, size, size, size, size, size * 8, size * 8);
		this.drawModalRectWithCustomSizedTexture(x, y, size * 5, size, size, size, size * 8, size * 8);
	}
	
	public void init() {}
	
	public void render() {}
	
	public int animate(int current, int var)
	{
		if(var < current) var += 1;
		else if(var > current) var -= 1;
		else return current;
		return var;
	}
	
	public static double animateSmooth(double current, double var, double speed)
	{
		var = (double)Math.round(var * 100) / 100;
		double vel = 0.01;
		if(var < current) vel = speed - (speed * (var / current));
		else vel = -(speed - (speed * (current / var)));
		return var + vel;
	}
	
	public static double animateSmooth(double current, double var)
	{
		return animateSmooth(current, var, 1);
	}
	
	public static void drawStringWithBackground(String msg, int x, int y, int color)
	{
		int b = 2;
		int w = fr.getStringWidth(msg);
		drawRect(x - b, y - b, x + w + b, y + 7 + b, Colors.getColor(0.5, 0x000000));
		fr.drawString(msg, x, y, color);
	}
	
	public static void drawRectSmooth(double left, double top, double right, double bottom, int color)
    {
        if (left < right)
        {
            double i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            double j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(left, bottom, 0.0D).endVertex();
        vertexbuffer.pos(right, bottom, 0.0D).endVertex();
        vertexbuffer.pos(right, top, 0.0D).endVertex();
        vertexbuffer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
