package com.github.unchama.seichiassist.listener;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.unchama.seichiassist.SeichiAssist;
import com.github.unchama.seichiassist.data.Mana;
import com.github.unchama.seichiassist.data.PlayerData;
import com.github.unchama.seichiassist.util.Util;

public class GachaItemListener implements Listener {
	HashMap<UUID,PlayerData> playermap = SeichiAssist.playermap;
	private SeichiAssist plugin = SeichiAssist.plugin;
	@EventHandler
	public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent e){
		Player player = e.getPlayer();
		PlayerData playerdata = playermap.get(player.getUniqueId());
		int level = playerdata.level;
		Mana mana = playerdata.activeskilldata.mana;
		ItemStack i = e.getItem();
		Material m = i.getType();
		ItemMeta itemmeta = i.getItemMeta();
		
		
		
		
		
		
		
		//これ以降は説明文あり
		if(!itemmeta.hasLore())return;
		List<String> lore = itemmeta.getLore();

		if(Util.LoreContains(lore,"マナ完全回復")){
			mana.fullMana(player,level);
			player.playSound(player.getLocation(),Sound.ENTITY_WITCH_DRINK, 1.0F, 1.2F);
		}
		
		if(Util.LoreContains(lore,"マナ回復（小）")){
			mana.increaseMana(300, player, level);
			player.playSound(player.getLocation(),Sound.ENTITY_WITCH_DRINK, 1.0F, 1.2F);
		}
		
		if(Util.LoreContains(lore,"マナ回復（中）")){
			mana.increaseMana(1500, player, level);
			player.playSound(player.getLocation(),Sound.ENTITY_WITCH_DRINK, 1.0F, 1.2F);
		}
		
		if(Util.LoreContains(lore,"マナ回復（大）")){
			mana.increaseMana(10000, player, level);
			player.playSound(player.getLocation(),Sound.ENTITY_WITCH_DRINK, 1.0F, 1.2F);
		}
		
		if(Util.LoreContains(lore,"マナ回復（極）")){
			mana.increaseMana(100000, player, level);
			player.playSound(player.getLocation(),Sound.ENTITY_WITCH_DRINK, 1.0F, 1.2F);
		}
	}

}
