package com.Akoot.kragen.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.Akoot.kragen.Refrence;

class Config
{
	private List<String> keys;
	private List<Object> datas;

	private CthFile file;

	public Config(CthFile file)
	{
		this.file = file;
		reload();
	}

	public void reload()
	{
		keys = new ArrayList<String>();
		datas = new ArrayList<Object>();
		for(String line: file.read())
		{
			if(!line.startsWith("#") && line.contains(":"))
			{
				String key = line.substring(0, line.indexOf(":"));
				Object data = file.get(key);

				keys.add(key);
				datas.add(data);
			}
		}
	}

	public Object get(String key)
	{
		return datas.get(keys.indexOf(key));
	}
	
	public void set(String key, Object data)
	{
		file.set(key, data);
		reload();
	}
}

public class Configs
{
	public static final File dir = new File(Refrence.MOD_NAME);
	public static final File configDir = new File(dir, "configuration");
	public static final CthFile config = new CthFile(configDir, "config.kr");
	private static Config cfg;

	public static void load()
	{
		cfg = new Config(config);
	}

	public static Object get(String key)
	{
		return cfg.get(key);
	}

	public static String getString(String key)
	{
		return get(key).toString();
	}

	public static int getInt(String key)
	{
		return Integer.parseInt(getString(key));
	}
	
	public static void set(String key, Object data)
	{
		cfg.set(key, data);
	}

	public static void init()
	{
		System.out.println("Loaded configuration files");
		if(!dir.exists()) dir.mkdir();
		if(!configDir.exists()) configDir.mkdir();
		if(!config.exists())
		{
			config.create();
			config.addComment("Basic Configuration");
			config.set("primary-color", "#FFFFFF");
			config.set("secondary-color", "#000000");
			config.addComment("The background of the main menu");
			config.addComment("Set to \"default\"  for vanilla background");
			config.set("background", "textures/gui/bg.png");
			config.addLine();
			config.addComment("X Y Z position colors");
			config.set("position.x.color", "#FFFFFF");
			config.set("position.y.color", "#FFFFFF");
			config.set("position.z.color", "#FFFFFF");
			config.addComment("X Y Z position position (on the screen)");
			config.set("position.x.position", "(0,0)");
			config.set("position.y.position", "(0,20)");
			config.set("position.z.position", "(0,30)");
			config.addComment("Direction colors");
			config.set("direction.north.color", "#FFFFFF");
			config.set("direction.south.color", "#FFFFFF");
			config.set("direction.east.color", "#FFFFFF");
			config.set("direction.west.color", "#FFFFFF");
			config.addComment("Direction position");
			config.set("direction.position", "(0,40)");
			config.addLine();
			config.addComment("Crosshair types: vanilla, dynamic, dynamic-color, dynamic-dynamic-color, normal, chevron, <any text>");
			config.set("crosshair.type", "vanilla");
			config.addComment("Custom crosshair size (non-vanilla)");
			config.set("crosshair.size", 8);
			config.addComment("Crosshair colors");
			config.set("crosshair.neutral.color", "#FFFFFF");
			config.addComment("These colors are only for dynamic-color crosshairs");
			config.set("crosshair.bad.color", "#FF0000");
			config.set("crosshair.normal.color", "#FFFF00");
			config.set("crosshair.good.color", "#00FF00");
		}
		if(!getModules().exists())
		{
			CthFile mod = getModules();
			mod.create();
			mod.addComment("Display X Y Z position");
			mod.set("xyzposition", true);
			mod.addComment("Enable elytra flight");
			mod.set("elytra", true);
			mod.addComment("Enable custom main menu");
			mod.set("mainmenu", true);
		}
		load();
	}

	public static CthFile getModules()
	{
		return new CthFile(dir, "modules.kr");
	}

	public static int getColor(String key)
	{
		return(getColor(config, key));
	}
	
	public static Delta getDelta(String key)
	{
		return getDelta(config, key);
	}
	
	public static Delta getDelta(CthFile config, String key)
	{
		String k = key + ".position";
		if(config.has(k))
		{
			return Delta.getDelta(config.getString(k));
		}
		return new Delta(0,0);
	}

	public static int getColor(CthFile config, String key)
	{
		String k = key + ".color";
		if(config.has(k))
		{
			return Colors.getColor(config.getString(k));
		}
		return -1;
	}
}
