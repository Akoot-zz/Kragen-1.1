package com.Akoot.kragen.handlers;

import java.io.File;

import com.Akoot.kragen.modules.Module;
import com.Akoot.kragen.modules.Modules;
import com.Akoot.kragen.refrence.ConfigValues;
import com.Akoot.kragen.refrence.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler
{
	public static Configuration configuration;

	private static final String GENERAL = Configuration.CATEGORY_GENERAL;
	private static final String MODULES = "modules";
	private static final String COSMETIC = "cosmetic";

	public static void init(File conf)
	{
		if(configuration == null)
		{
			configuration = new Configuration(conf);
			loadConfiguration();
		}
	}

	public static Configuration getConfig()
	{
		return configuration;
	}

	public static void loadConfiguration()
	{
		configuration.load();
		ConfigValues.primaryColor = configuration.get(COSMETIC, "primaryColor", "#FFFFFF", "primary color of the mod").getString();
		ConfigValues.secondaryColor = configuration.get(COSMETIC, "primaryColor", "#000000", "secondary color of the mod").getString();
		
		for(Module module: Modules.getModules())
		{
			boolean enabled = configuration.get(MODULES, module.getName(), false, module.getDescription()).getBoolean();
			module.setEnabled(enabled);
		}
		
		if (configuration.hasChanged()) configuration.save();
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equalsIgnoreCase(Reference.MOD_ID)) loadConfiguration();
	}
}
