package com.Akoot.kragen.modules;

import java.util.ArrayList;
import java.util.List;

import com.Akoot.kragen.util.Configs;

public enum Modules
{
	XYZPOSITION,
	ELYTRA,
	MAINMENU,
	PLAYERS,
	CURSOR,
	AUTOBLOCK
	;

	public static boolean isEnabled(Modules mod)
	{
		return Configs.getModules().getBoolean(mod.name().toLowerCase());
	}
	
	public static void setEnabled(Modules mod, boolean enabled)
	{
		if(Configs.getModules().getBoolean(mod.name().toLowerCase()) != enabled) Configs.getModules().set(mod.name().toLowerCase(), enabled);
	}
	
	public static List<Module> getModules()
	{
		List<Module> modules = new ArrayList<Module>();
		modules.add(new PlayerInfo());
		//modules.add(new AutoBlock());
		return modules;
	}
	
	public static Module getModule(Modules module)
	{
		for(Module mod: getModules()) if(mod.mod == module) return mod;
		return null;
	}
	
	public static void render(Modules module)
	{
		getModule(module).render();
	}
}
