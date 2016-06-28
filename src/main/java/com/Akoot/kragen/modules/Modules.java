package com.Akoot.kragen.modules;

import java.util.ArrayList;
import java.util.List;

import com.Akoot.kragen.handlers.ConfigurationHandler;

public enum Modules
{
	PLAYERS,
	AUTOBLOCK
	;

	public boolean isEnabled()
	{
		return getModule(this).isEnabled();
	}

	public static List<Module> getModules()
	{
		List<Module> modules = new ArrayList<Module>();
		modules.add(new PlayerInfo());
		modules.add(new AutoBlock());
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
