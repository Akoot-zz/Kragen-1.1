package com.Akoot.kragen.modules;

import java.util.List;

import com.Akoot.kragen.util.Colors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class PlayerInfo extends Module
{
	private List<EntityPlayer> players;

	protected final ResourceLocation icons = new ResourceLocation("textures/gui/icons.png");

	public PlayerInfo()
	{
		this.mod = Modules.PLAYERS;
		this.height = 10;
		this.width = 10;
	}

	public void render()
	{
		super.render();
		players = mc.theWorld.playerEntities;
		for(EntityPlayer player: players) renderPlayerInfo(player, players.indexOf(player));
	}

	public void click()
	{
		System.out.println(mc.getSystemTime());
	}

	public void renderPlayerInfo(EntityPlayer player, int place)
	{
		int distance = (int) (mc.thePlayer.getDistance(player.posX, player.posY, player.posZ));
		y = 10 + y + place;
		drawRect(x, y, x + width + fr.getStringWidth(distance+""), y + height, Colors.getColor(0.2, 0x000000));
		this.fixColor();
		this.drawPlayerHead(x + 1, y + 1, height - 2, player.getName());
		fr.drawString(distance+"", x + height, y + 1, 0xffffffff);

		if(isOver())
		{
			x += width + fr.getStringWidth(distance+"");
			drawRect(x, y, x + fr.getStringWidth(player.getName()) + 92, y + height, Colors.getColor(0.2, 0x000000));
			fr.drawString(TextFormatting.AQUA + player.getName(), x + 1, y + 1, 0xffffffff);

			this.fixColor();

			height /= 2;

			x += fr.getStringWidth(player.getName()) + 1;

			mc.getTextureManager().bindTexture(icons);
			
			int size;

			for(int i = 0; i < player.getMaxHealth() / 2; i++)
			{
				this.drawModalRectWithCustomSizedTexture(x + (i * 9), y, 16, 0, 9, 9, 256, 256);
			}
			
			for(int i = 0; i < player.getHealth() / 2; i++)
			{
				this.drawModalRectWithCustomSizedTexture(x + (i * 9), y, 52, 0, 9, 9, 256, 256);
			}
		}
	}
}
