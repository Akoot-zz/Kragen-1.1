package com.Akoot.kragen.modules;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.Akoot.kragen.gui.elements.GuiClickable;
import com.Akoot.kragen.input.Keybind;
import com.Akoot.kragen.input.Keybinds;
import com.Akoot.kragen.util.Colors;
import com.Akoot.kragen.util.EntityUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.TextFormatting;

public class PlayerInfo extends Module
{
	private List<EntityPlayer> players;

	protected final ResourceLocation icons = new ResourceLocation("textures/gui/icons.png");

	public PlayerInfo()
	{
		this.mod = Modules.PLAYERS;
		this.render = true;
		this.keybind = new Keybind(25, 25, true);
		this.height = 11;
	}

	public void render()
	{
		players = mc.theWorld.playerEntities;
		int max = 5;
		Collections.sort(players, new Comparator<EntityPlayer>()
		{
			@Override
			public int compare(EntityPlayer p1, EntityPlayer p2)
			{
				int d1 = (int) (mc.thePlayer.getDistance(p1.posX, p1.posY, p1.posZ));
				int d2 = (int) (mc.thePlayer.getDistance(p2.posX, p2.posY, p2.posZ));
				if(d1 > d2) return 1;
				if (d1 < d2) return -1;
				return 0;
			}
		});
		for(EntityPlayer player: players) if(/*player != mc.thePlayer &&*/ players.indexOf(player) <= max) renderPlayerInfo(player, players.indexOf(player));
		if(players.size() > max)
		{
			int left = players.size() - max;
			String txt = "And " + TextFormatting.AQUA + left + TextFormatting.RESET + " more...";
			drawRect(x, y + (max * height), x + 32, y + (max * height) + 6, Colors.getColor(0.15, 0x000000));
			this.drawStringWithScale(txt, x, y + 1 + (max * height), 0xffffffff, 0.5);
		}
	}

	public void renderPlayerInfo(EntityPlayer player, int place)
	{
		//place -= 1;
		int yy = y + place * height;
		PlayerHead info = new PlayerHead(mc, player);
		info.draw(x, yy, height);
	}
}

class PlayerHead extends GuiClickable
{
	private EntityPlayer player;

	public PlayerHead(Minecraft mc, EntityPlayer player)
	{
		super(mc);
		this.player = player;
	}

	private boolean facingPlayer()
	{
		if(mc.objectMouseOver.typeOfHit == Type.ENTITY && mc.objectMouseOver.entityHit != null)
		{
			Entity entity = mc.objectMouseOver.entityHit;
			if(entity instanceof EntityPlayer)
			{
				if(entity == player) return true;
			}
		}
		return false;
	}

	public void draw(int x, int y, int size)
	{
		height = size;
		this.x = x;
		this.y = y;

		int distance = (int) (mc.thePlayer.getDistance(player.posX, player.posY, player.posZ));
		int dist = fr.getStringWidth(distance+"");
		int name = fr.getStringWidth(player.getName());

		//distance = 420;

		width = size + dist + name + 2;

		int centerY = y + (size / 4); 

		drawRect(x, y, x + width, y + size, Colors.getColor(isOver() ? 0.4 : 0.2, 0x000000));
		this.fixColor();
		this.drawPlayerHead(x + 1, y + 1, size - 2, player.getName());
		fr.drawString(TextFormatting.WHITE + player.getName(), x + size + 1, centerY, 0xffffffff);
		fr.drawString(TextFormatting.GOLD + "" + distance, x + width - dist, centerY, 0xffffffff);

		if(isDown() || (facingPlayer() && Keybinds.KEY_INV_PLAYER.isDown))
		{
			mc.displayGuiScreen(new GuiInventory(player));
		}

		if(isOver() || facingPlayer())
		{
			double percentHealth = player.getHealth() / player.getMaxHealth();
			double percentAbsorb = player.getAbsorptionAmount() / player.getMaxHealth();
			double percentHunger = (double) (player.getFoodStats().getFoodLevel()) / 20.0;
			double percentArmor = EntityUtil.getArmorRating(player) / 100.0;
			double percentEnchant = EntityUtil.getArmorEnchantRating(player) / 100.0;
			
			int bar = 35;
			int bar2 = percentArmor > 0.0 ? 1 : 0;
			
			//bgs
			drawRect(x + width, y, x + width + bar + bar2, y + size, Colors.getColor(0.3, 0x000000));
			drawRect(x + width + 1, y + 1, x + width + bar - bar2 - 1, y + (height / 2), Colors.getColor(0.15, 0x000000));
			drawRect(x + width + 1, y + (height / 2) + 1, x + width + bar - bar2 - 1, y  + height - 1, Colors.getColor(0.15, 0x000000));
			drawRect(x + width + bar - bar2, y + 1, x + width + bar + bar2 - 1, y + height - 1, Colors.getColor(0.15, 0x000000));

			int health = 0xffFF3043;
			int hunger = 0xffF87616;
			
			if(player.isPotionActive(MobEffects.HUNGER))
			{
				hunger = 0xff759D41;
			}
			
			if(player.isPotionActive(MobEffects.HEALTH_BOOST) || player.isPotionActive(MobEffects.REGENERATION))
			{
				health = 0xffFF7FB5;
			}
			
			if(player.isPotionActive(MobEffects.RESISTANCE))
			{
				health = 0xffCF98E8;
			}
			
			if(player.isPotionActive(MobEffects.WEAKNESS))
			{
				health = 0xff9C3555;
			}
			
			if(player.isPotionActive(MobEffects.POISON))
			{
				health = 0xff759D41;
			}
			
			if(player.isPotionActive(MobEffects.WITHER))
			{
				health = 0xff222222;
			}
			
			//health
			drawRect(x + width + 1, y + 1, x + width + (int) (bar * percentHealth) - bar2 - 1, y + (height / 2), health);
			if(player.getAbsorptionAmount() > 0.0) drawRect(x + width + 1, y + 1, x + width + (int) (bar - 2 * percentAbsorb) - bar2 - 1, y + (height / 2), Colors.getColor(0.7, 0xFFCE26));
			
			//hunger
			drawRect(x + width + 1, y + (height / 2) + 1, x + width + (int) (bar * percentHunger) - bar2, y + height - 1, hunger);
			
			//armor
			if(percentArmor > 0.0) drawRect(x + width + bar - bar2, y + 1 + (int) (height * (1.0 - percentArmor)), x + width + bar + bar2 - 1, y + height - 1, 0xff008AFF);
			if(percentEnchant > 0.0) drawRect(x + width + bar - bar2, y + 1 + (int) (height * (1.0 - percentEnchant)), x + width + bar + bar2 - 1, y + height - 1, Colors.getColor(0.7, 0xB63BEF));
		}
	}
}
