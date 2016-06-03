package com.Akoot.kragen.events;

import com.Akoot.kragen.input.Keybinds;
import com.Akoot.kragen.modules.Module;
import com.Akoot.kragen.modules.Modules;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class OnTickEvent
{
	private Minecraft mc;
	
	public OnTickEvent(Minecraft mc)
	{
		this.mc = mc;
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event)
	{
		Keybinds.tick();
		for(Module mod: Modules.getModules())
		{
			if(mod.isEnabled()) mod.render();
		}
	}
}
