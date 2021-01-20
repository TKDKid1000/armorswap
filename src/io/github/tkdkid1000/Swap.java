package io.github.tkdkid1000;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Swap extends JavaPlugin implements Listener {
	FileConfiguration config = getConfig();
	Plugin plugin = this;
	
	// onEnable method
	@Override
	public void onEnable() {
		plugin.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	// onDisable method
	@Override
	public void onDisable() {
		
	}
	
	// event for the while thing
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		// get the player type
		Player p = e.getPlayer();
		// get the hand, if it is the offhand then return
		if (e.getHand() == EquipmentSlot.OFF_HAND) {
			return;
		}
		// get the actions
		Action a = e.getAction();
		// detect if the action is RIGHT_CLICK_AIr
		if (a.equals(Action.RIGHT_CLICK_AIR)) {
			List<World> worlds = new ArrayList<World>();
			for (String world : config.getStringList("worlds")) {
				worlds.add(Bukkit.getWorld(world));
			}
			if (worlds.contains(p.getWorld())) {
				if (e.getHand() == EquipmentSlot.HAND) {
					swapItems(p);
				}
			}
		} else {
			return;
		}
	}
	
	// swapping items function
	public void swapItems(Player p) {
		// get the helmet, chestplate, leggings, and boots
		ItemStack helm = p.getInventory().getHelmet();
		ItemStack chest = p.getInventory().getChestplate();
		ItemStack legs = p.getInventory().getLeggings();
		ItemStack boots = p.getInventory().getBoots();
		// get the held item
		ItemStack item = p.getInventory().getItemInMainHand();
		//check held item isnt air
		if (item == null | item.getType() == Material.AIR) {
			return;
		}
		Material type = item.getType();
		// check if held item is a type of item
		// helmets
		if (type == Material.NETHERITE_HELMET
				|| type == Material.DIAMOND_HELMET
				|| type == Material.IRON_HELMET
				|| type == Material.CHAINMAIL_HELMET
				|| type == Material.GOLDEN_HELMET
				|| type == Material.LEATHER_HELMET
				|| type == Material.TURTLE_HELMET) {
			// swap items
			if (helm == null || helm.getType() == Material.AIR) {
				return;
			}
			p.getInventory().setItemInMainHand(helm);
			p.getInventory().setHelmet(item);
		}
		// chestplates
		else if (type == Material.NETHERITE_CHESTPLATE
				|| type == Material.DIAMOND_CHESTPLATE
				|| type == Material.IRON_CHESTPLATE
				|| type == Material.CHAINMAIL_CHESTPLATE
				|| type == Material.GOLDEN_CHESTPLATE
				|| type == Material.LEATHER_CHESTPLATE
				|| type == Material.ELYTRA) {
			// swap items
			if (chest == null || chest.getType() == Material.AIR) {
				return;
			}
			p.getInventory().setItemInMainHand(chest);
			p.getInventory().setChestplate(item);
		}
		// leggings
		else if (type == Material.NETHERITE_LEGGINGS
				|| type == Material.DIAMOND_LEGGINGS
				|| type == Material.IRON_LEGGINGS
				|| type == Material.CHAINMAIL_LEGGINGS
				|| type == Material.GOLDEN_LEGGINGS
				|| type == Material.LEATHER_LEGGINGS) {
			// swap items
			if (legs == null || legs.getType() == Material.AIR) {
				return;
			}
			p.getInventory().setItemInMainHand(legs);
			p.getInventory().setLeggings(item);
		}
		// boots
		else if (type == Material.NETHERITE_BOOTS
				|| type == Material.DIAMOND_BOOTS
				|| type == Material.IRON_BOOTS
				|| type == Material.CHAINMAIL_BOOTS
				|| type == Material.GOLDEN_BOOTS
				|| type == Material.LEATHER_BOOTS) {
			// swap items
			if (boots == null || boots.getType() == Material.AIR) {
				return;
			}
			p.getInventory().setItemInMainHand(boots);
			p.getInventory().setBoots(item);
		}
		else {
			return;
		}
	}
}
