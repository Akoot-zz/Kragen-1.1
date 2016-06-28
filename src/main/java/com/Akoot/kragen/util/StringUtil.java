package com.Akoot.kragen.util;

public class StringUtil
{	
	private static final String key = "abcdefghijklmnopqrstuvwxyz0123456789";

	public static String encrypt(String msg, int index)
	{
		String encryption = "";
		for(char c: msg.toCharArray())
		{
			int letter;
			String ch = Character.toString(c);
			if(key.contains(ch))
			{
				letter = key.indexOf(ch) + index;
				while(letter >= key.length())
				{
					letter -= key.length();
				}
				encryption += key.substring(letter, letter + 1);
			}
			else
			{
				encryption += ch;
			}
		}
		return encryption;
	}
	
	public static String decrypt(String msg, int index)
	{
		return encrypt(msg, key.length() - index);
	}
	
	public static String encrypt(String msg)
	{
		return encrypt(msg, 1);
	}
	
	public static String decrypt(String msg)
	{
		return decrypt(msg, 1);
	}
}
