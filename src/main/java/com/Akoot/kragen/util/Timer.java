package com.Akoot.kragen.util;

public class Timer
{

	private double time;
	private int ticks;

	public Timer(double seconds)
	{
		time = seconds;
	}

	private void tick()
	{
		if(ticks % (20 * time) == 0)
		{
			function();
		}
		ticks++;
	}
	
	public void function() {}
}
