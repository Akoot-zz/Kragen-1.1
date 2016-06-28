package com.Akoot.kragen.input;

import com.Akoot.kragen.refrence.Reference;

import net.minecraft.client.settings.KeyBinding;

public class Keybind
{
	public int bind1;
	public int bind2;

	public boolean isDown;
	public boolean isToggled;
	
	public String name;
	
	public KeyBinding keybinding;
	
	public Keybind(String name, int bind)
	{
		this(name, bind, bind);
	}
	
	/**
	 * @param name Display name for menus
	 * @param bind1 Primary keybind
	 * @param bind2 Alternate keybind
	 * @param toggle Whether or not it shall be toggled
	 */
	public Keybind(String name, int bind1, int bind2)
	{
		this.name = name;
		this.bind1 = bind1;
		this.bind2 = bind2;
		this.keybinding = new KeyBinding(name, bind1, Reference.MOD_NAME);
	}

	public void toggle()
	{
		if(isToggled) isToggled = false;
		else isToggled = true;
	}

	public void press()
	{
		isDown = true;
	}

	public void release()
	{
		isDown = false;
	}
}