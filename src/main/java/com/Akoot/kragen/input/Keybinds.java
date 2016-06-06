package com.Akoot.kragen.input;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.Akoot.kragen.modules.Module;
import com.Akoot.kragen.modules.Modules;

public class Keybinds
{
	private static List<Keybind> keybinds = new ArrayList<Keybind>();

	public static Keybind KEY_CURSOR = new Keybind(56, 184, true);
	public static Keybind KEY_INV_PLAYER = new Keybind(47, 47, false);

	public static void init()
	{
		keybinds.add(KEY_CURSOR);
		keybinds.add(KEY_INV_PLAYER);
	}

	public static List<Keybind> getBinds()
	{
		return keybinds;
	}

	public static void tick()
	{
		for(Module mod: Modules.getModules())
		{
			keybinds.add(mod.keybind);
		}
		for(Keybind keybind: keybinds)
		{
			if(Keyboard.isKeyDown(keybind.bind1) || Keyboard.isKeyDown(keybind.bind2))
			{
				if(keybind.toggle && !keybind.isDown) keybind.toggle();
				keybind.press();
				return;
			}
			keybind.release();
		}
	}
}
