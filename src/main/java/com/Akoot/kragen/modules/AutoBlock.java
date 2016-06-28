package com.Akoot.kragen.modules;

import com.Akoot.kragen.input.Keybind;

import net.minecraft.client.Minecraft;

public class AutoBlock extends Module
{
	public AutoBlock()
	{
		this.mod = Modules.AUTOBLOCK;
		this.keybind = new Keybind("Auto Block", 48);
		this.name = "Auto Block";
		this.description = "Automatically blocks attacks";
	}

	@Override
	public void render()
	{
	}

	@Override
	public void onEntityUpdate()
	{
		
	}
}
