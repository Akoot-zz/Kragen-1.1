package com.Akoot.kragen.modules;

import com.Akoot.kragen.input.Keybind;
import com.Akoot.kragen.util.Colors;
import com.mojang.realmsclient.gui.ChatFormatting;

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
		String txt = "autoblock is on m8";
		int length = fr.getStringWidth(txt) + 2;
		drawRect(this.getCenter().x - length / 2, 0, this.getCenter().x + length / 2, 10, Colors.getColor(0.2, 0x000000));
		this.drawCenteredString(fr, ChatFormatting.AQUA + txt, this.getCenter().x, 1, 0xffffffff);
	}

	@Override
	public void onEntityUpdate()
	{
	}
}
