package com.Akoot.kragen.util;

public enum MaterialColor
{
	DIAMOND(0xff4FC5D0),
	GOLD(0xffFAD91F),
	IRON(0xffD9D9D9),
	LEATHER(0xffCF9D70);
	
	private int color;
	
	private MaterialColor(int color)
	{
		this.color = color;
	}
	
	public int color()
	{
		return color;
	}
	
	public String toString()
	{
		return "#" + (color + "").substring(4);
	}
}
