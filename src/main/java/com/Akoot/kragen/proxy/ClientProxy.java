package com.Akoot.kragen.proxy;

import com.Akoot.kragen.events.GuiEvent;
import com.Akoot.kragen.events.OnTickEvent;
import com.Akoot.kragen.input.Keybind;
import com.Akoot.kragen.input.Keybinds;
import com.Akoot.kragen.modules.Module;
import com.Akoot.kragen.modules.Modules;
import com.Akoot.kragen.refrence.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
	private Minecraft mc = Minecraft.getMinecraft();

	private void registerEvents()
	{
		MinecraftForge.EVENT_BUS.register(new GuiEvent(mc));
		MinecraftForge.EVENT_BUS.register(new OnTickEvent(mc));
	}

	private void registerKeyBindings()
	{
		for(Keybind keybind: Keybinds.getBinds()) ClientRegistry.registerKeyBinding(keybind.keybinding);
		for(Module mod: Modules.getModules()) ClientRegistry.registerKeyBinding(mod.keybind.keybinding);
	}

	@Override
	public void preInit()
	{
		registerKeyBindings();
	}

	@Override
	public void init()
	{
		registerEvents();
	}

	@Override
	public void postInit()
	{
		/* NOOP */
	}
}
