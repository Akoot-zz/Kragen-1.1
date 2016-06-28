package com.Akoot.kragen.modules;

import com.Akoot.kragen.client.gui.elements.GuiDraggable;
import com.Akoot.kragen.input.Keybind;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class Module extends GuiDraggable
{
	public Modules mod;
	public Keybind keybind;
	protected boolean enabled;
	
	protected String name;
	protected String description;
	
	public Module()
	{
		super(Minecraft.getMinecraft());
	}
	
	public void setEnabled(boolean enable)
	{
		this.enabled = enable;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void onEntityUpdate() {}
}
