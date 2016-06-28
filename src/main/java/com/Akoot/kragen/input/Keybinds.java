package com.Akoot.kragen.input;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.Akoot.kragen.modules.Module;
import com.Akoot.kragen.modules.Modules;

import net.minecraftforge.fml.common.gameevent.InputEvent;

public class Keybinds
{
	public static Keybind KEY_CURSOR = new Keybind("Show menu", 56, 184);
	public static Keybind KEY_INV_PLAYER = new Keybind("Show player inventory", 47);

	public static Keybind[] getBinds()
	{
		Keybind[] keybinds = {KEY_CURSOR, KEY_INV_PLAYER};
		return keybinds;
	}
	
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
	{
		for(Module mod: Modules.getModules()) if(mod.keybind.keybinding.isPressed()) mod.setEnabled(!mod.isEnabled());
		for(Keybind keybind: getBinds())
		{
			if(keybind.keybinding.isPressed()) keybind.toggle();
			else if(keybind.keybinding.isKeyDown()) keybind.press();
			else keybind.release();
		}
	}
}
