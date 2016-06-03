package com.Akoot.kragen.gui.elements;

import org.lwjgl.input.Mouse;

import com.Akoot.kragen.util.Delta;

import net.minecraft.client.Minecraft;

public class GuiClickable extends GuiElement
{	
	private boolean click = false;
	private boolean clicked = false;
	
	public GuiClickable(Minecraft mc)
	{
		super(mc);
	}
	
	public boolean isOver()
	{
		return getMouse().x >= x && getMouse().y >= y && getMouse().x < x + width && getMouse().y < y + height;
	}
	
	public boolean isDown()
	{
		return isOver() && Mouse.isButtonDown(0);
	}
	
	public void click(){}
	
	public void render()
	{
		//click
	}
}
