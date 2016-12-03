package com.Akoot.kragen.events;

import com.Akoot.kragen.client.gui.GuiCursor;
import com.Akoot.kragen.client.gui.elements.GuiElement;
import com.Akoot.kragen.input.Keybinds;
import com.Akoot.kragen.modules.Module;
import com.Akoot.kragen.modules.Modules;
import com.Akoot.kragen.refrence.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiEvent extends Gui
{
	private Minecraft mc;
	private FontRenderer fr;

	public GuiEvent(Minecraft mc)
	{
		this.mc = mc;
		this.fr = mc.fontRendererObj;
	}

	@SubscribeEvent
	public void onRenderInGame(RenderGameOverlayEvent event)
	{
		if(event.getType() == ElementType.TEXT)
		{
			for(Module mod: Modules.getModules()) mod.render();
			
			if(Keybinds.KEY_CURSOR.isDown && mc.currentScreen == null) mc.displayGuiScreen(new GuiCursor());
			if((!Keybinds.KEY_CURSOR.isDown) && mc.currentScreen instanceof GuiCursor) mc.displayGuiScreen(null);
		}
	}

	@SubscribeEvent
	public void onRenderGuiScreen(GuiScreenEvent event)
	{
		if(event.getGui() instanceof GuiMainMenu)
		{
			fr.drawString("testees", 0, 0, 0xffff0000);
		}
	}
}
