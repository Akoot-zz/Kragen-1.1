package com.Akoot.kragen.modules;

import com.Akoot.kragen.Kragen;
import com.Akoot.kragen.gui.elements.GuiDraggable;

import net.minecraft.client.Minecraft;

public class Module extends GuiDraggable
{
	public Modules mod;
	
	public Module()
	{
		super(Minecraft.getMinecraft());
	}
	
	public boolean isEnabled()
	{
		return Modules.isEnabled(mod);
	}
	
	public void enable()
	{
		Modules.setEnabled(mod, true);
	}
	
	public void disable()
	{
		Modules.setEnabled(mod, false);
	}
	
	@Override
	public void render()
	{
		super.render();
	}
}
