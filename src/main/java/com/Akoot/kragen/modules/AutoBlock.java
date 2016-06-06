package com.Akoot.kragen.modules;

import com.Akoot.kragen.input.Keybind;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class AutoBlock extends Module
{
	public AutoBlock()
	{
		this.mod = Modules.AUTOBLOCK;
		this.keybind = new Keybind(48, 48, true);
		this.render = true;
	}

	@Override
	public void render()
	{
		fr.drawString("autoblock", 0, 0, 0xffff0000);
		fr.drawString(mc.thePlayer.moveStrafing + "", 0, 10, 0xffff0000);
	}
	
	boolean dodge;

	@Override
	public void onEntityUpdate()
	{
		
	}
}
