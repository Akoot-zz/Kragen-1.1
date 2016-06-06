package com.Akoot.kragen.modules;

import com.Akoot.kragen.Kragen;
import com.Akoot.kragen.gui.elements.GuiDraggable;
import com.Akoot.kragen.input.Keybind;

import net.minecraft.client.Minecraft;

public class Module extends GuiDraggable
{
	public Modules mod;
	public Keybind keybind;
	public boolean render;
	
	public Module()
	{
		super(Minecraft.getMinecraft());
	}
	
	public boolean shouldRender()
	{
		return render;
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
	/**
	 * Add this if you want it to be clicked and dragged, or to render things
	 */
	@Override
	public void render()
	{
		super.render();
	}
	
	public void onEntityUpdate() {}
}
