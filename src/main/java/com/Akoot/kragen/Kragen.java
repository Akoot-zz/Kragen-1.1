package com.Akoot.kragen;

import com.Akoot.kragen.events.GuiEvent;
import com.Akoot.kragen.events.OnTickEvent;
import com.Akoot.kragen.handlers.ConfigurationHandler;
import com.Akoot.kragen.input.Keybinds;
import com.Akoot.kragen.modules.Modules;
import com.Akoot.kragen.proxy.CommonProxy;
import com.Akoot.kragen.refrence.Reference;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Kragen 
{
	public static Minecraft mc;
	private static ConfigurationHandler config;

	@Instance(Reference.MOD_ID)
	public static Kragen instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
//		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
//		MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}
}
