package com.Akoot.kragen.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class EntityUtil
{	
	public static boolean isFriendlyPlayer(EntityPlayer player)
	{
		if(player.isOnSameTeam(Minecraft.getMinecraft().thePlayer)) return true;
		return false;
	}

	public static boolean isAgressiveNow(Entity entity)
	{
		if(canBeAgressive(entity))
		{
			if(entity.hitByEntity(Minecraft.getMinecraft().thePlayer)) return true;
		}
		else
		{
			if(entity instanceof EntityMob) return true;
		}
		return false;
	}

	public static boolean canBeAgressive(Entity entity)
	{
		if(entity instanceof EntityIronGolem || entity instanceof EntitySpider || entity instanceof EntityEnderman || entity instanceof EntityWolf) return true;
		return false;
	}

	public static double getArmorRating(EntityPlayer player)
	{
		double rating = 0;
		Iterable<ItemStack> armors = player.getArmorInventoryList();
		for(ItemStack item: armors)
		{
			if(item != null)
			{
				if(item.getItem() instanceof ItemArmor)
				{
					ItemArmor armor = (ItemArmor) item.getItem();			

					if(armor.armorType == EntityEquipmentSlot.CHEST) rating += 8;
					else if(armor.armorType == EntityEquipmentSlot.HEAD) rating += 5;
					else if(armor.armorType == EntityEquipmentSlot.LEGS) rating += 7;
					else if(armor.armorType == EntityEquipmentSlot.FEET) rating += 4;

					if(armor.getArmorMaterial() == ArmorMaterial.DIAMOND) rating += 19;
					else if(armor.getArmorMaterial() == ArmorMaterial.CHAIN || armor.getArmorMaterial() == ArmorMaterial.IRON) rating += 14;
					else if(armor.getArmorMaterial() == ArmorMaterial.GOLD) rating += 10;
					else if(armor.getArmorMaterial() == ArmorMaterial.LEATHER) rating += 6;
					
					rating -= armor.getDurabilityForDisplay(item);
				}
			}
		}
		return rating;
	}

	public static double getArmorEnchantRating(EntityPlayer player)
	{
		double rating = 0;
		Iterable<ItemStack> armors = player.getArmorInventoryList();
		for(ItemStack item: armors)
		{
			if(item != null)
			{
				if(item.isItemEnchanted())
				{
					ItemArmor armor = (ItemArmor) item.getItem();							
					if(armor.armorType == EntityEquipmentSlot.CHEST) rating += 8;
					else if(armor.armorType == EntityEquipmentSlot.HEAD) rating += 5;
					else if(armor.armorType == EntityEquipmentSlot.LEGS) rating += 7;
					else if(armor.armorType == EntityEquipmentSlot.FEET) rating += 4;

					if(armor.getArmorMaterial() == ArmorMaterial.DIAMOND) rating += 19;
					else if(armor.getArmorMaterial() == ArmorMaterial.CHAIN || armor.getArmorMaterial() == ArmorMaterial.IRON) rating += 14;
					else if(armor.getArmorMaterial() == ArmorMaterial.GOLD) rating += 10;
					else if(armor.getArmorMaterial() == ArmorMaterial.LEATHER) rating += 6;
				}
			}
		}
		return rating;
	}

}
