package com.Akoot.kragen.client.gui.elements;

import net.minecraft.client.Minecraft;

public class GuiDraggable extends GuiClickable
{
	protected int lastX;
	protected int lastY;
	
	public GuiDraggable(Minecraft mc)
	{
		super(mc);
		lastX = x;
		lastY = y;
	}
	
	/**
	 * Please super this if you override, or else dragging will not work.
	 */
	@Override
	public void tick()
	{
		//TODO: Drag
		if(isDown())
		{
			x = getMouse().x;
			y = getMouse().y;
		}
		
		lastX = x;
		lastY = y;
	}
}
