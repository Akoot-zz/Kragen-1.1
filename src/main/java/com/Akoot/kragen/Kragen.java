package com.Akoot.kragen;

import com.Akoot.kragen.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Refrence.MOD_ID, name = Refrence.MOD_NAME, version = Refrence.VERSION)
public class Kragen 
{
	public static Minecraft mc;
	
	@Instance(Refrence.MOD_ID)
	public static Kragen instance;

	@SidedProxy(clientSide = "com.Akoot.kragen.proxy.ClientProxy", serverSide = "com.Akoot.kragen.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		mc = Minecraft.getMinecraft();
		//Configs.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//Keybinds.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
//		MinecraftForge.EVENT_BUS.register(new GuiEvent(mc));
//		MinecraftForge.EVENT_BUS.register(new OnTickEvent(mc));
	}
}
