package com.Akoot.kragen.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;

public class StringUtil
{
	private static final int size = 15;
	
	private static final String key = "abcdefghijklmnopqrstuvwxyz0123456789";

	public static String encrypt(String msg, int index)
	{
		String encryption = "";
		String key2 = Configs.getString("algorithm");
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
				encryption += key2.substring(letter, letter + 1);
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
