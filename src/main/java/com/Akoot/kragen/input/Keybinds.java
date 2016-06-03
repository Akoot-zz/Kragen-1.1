package com.Akoot.kragen.input;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lwjgl.input.Keyboard;

public class Keybinds
{
	private static List<Keybind> keybinds = new ArrayList<Keybind>();

	public static Keybind KEY_CURSOR = new Keybind(56, 184, true);
	public static Keybind KEY_EXIT = new Keybind(1, 1, true);

	public static void init()
	{
		keybinds.add(KEY_CURSOR);
	}

	public static List<Keybind> getBinds()
	{
		return keybinds;
	}

	public static void tick()
	{
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
