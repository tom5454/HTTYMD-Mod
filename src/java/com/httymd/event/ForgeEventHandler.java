package com.httymd.event;

import com.httymd.registry.DropRegistry;
import com.httymd.registry.NameRegistry;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.NameFormat;

public class ForgeEventHandler {

	/**
	 * Handles and manipulates registered drops according to current system
	 */
	@SubscribeEvent
	public void onEvent(LivingDropsEvent event) {
		for (ItemStack stack : DropRegistry.handleDropStacks(event.entityLiving, event.source, event.lootingLevel,
				event.recentlyHit, event.specialDropValue))
			event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY,
					event.entity.posZ, stack));
	}
	
	/**
	 * Reformats a player's name based on our current naming system
	 */
	@SubscribeEvent
	public void onEvent(NameFormat event) {
		event.displayname = NameRegistry.getDisplayName(event.entityLiving, event.username);
	}
}
