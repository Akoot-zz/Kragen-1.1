package com.Akoot.kragen.events;

import com.Akoot.kragen.gui.GuiCursor;
import com.Akoot.kragen.gui.elements.GuiClickable;
import com.Akoot.kragen.gui.elements.GuiDraggable;
import com.Akoot.kragen.input.Keybinds;
import com.Akoot.kragen.modules.Modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiEvent extends Gui
{
	private Minecraft mc;

	public GuiEvent(Minecraft mc)
	{
		this.mc = mc;
	}

	@SubscribeEvent
	public void onRenderInGame(RenderGameOverlayEvent event)
	{
		if(event.getType() == ElementType.TEXT)
		{
			//if(Modules.isEnabled(Modules.XYZPOSITION)) drawXYZ.render();		
			Modules.render(Modules.PLAYERS);

			if(Keybinds.KEY_CURSOR.isToggled && mc.currentScreen == null)
			{
				mc.displayGuiScreen(new GuiCursor());
			}
			if((!Keybinds.KEY_CURSOR.isToggled || Keybinds.KEY_EXIT.isDown) && mc.currentScreen instanceof GuiCursor)
			{
				mc.displayGuiScreen(null);
			}
		}
	}

	@SubscribeEvent
	public void onRenderGuiScreen(GuiScreenEvent event)
	{
	}
}
