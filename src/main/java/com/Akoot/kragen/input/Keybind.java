package com.Akoot.kragen.input;

public class Keybind
{
	public int bind1;
	public int bind2;

	public boolean isDown;
	public boolean isToggled;
	
	public boolean toggle;
	
	public Keybind(int bind1, int bind2)
	{
		this.bind1 = bind1;
		this.bind2 = bind2;
		this.toggle = false;
	}
	
	public Keybind(int bind1, int bind2, boolean toggle)
	{
		this.bind1 = bind1;
		this.bind2 = bind2;
		this.toggle = toggle;
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