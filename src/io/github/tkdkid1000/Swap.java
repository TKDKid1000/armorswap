package io.github.tkdkid1000;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Swap extends JavaPlugin implements Listener {
	FileConfiguration config = getConfig();
	List<World> worlds = new ArrayList<World>();
	Plugin plugin = this;

	// onEnable method
	@Override
	public void onEnable() {
		plugin.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		getWorlds();
	}

	// onDisable method
	@Override
	public void onDisable() {

	}

	public  void getWorlds(){
		worlds.clear();
		for (String world : config.getStringList("worlds")) {
			worlds.add(Bukkit.getWorld(world));
		}
	}

	// event for the while thing
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		// get the player type
		Player p = e.getPlayer();
		// get the actions
		Action a = e.getAction();
		// detect if the action is RIGHT_CLICK_AIr
		if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (worlds.contains(p.getWorld()) || worlds.size()==0) {
				swapItems(p);
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
		if (item.getType() == null | item.getType() == Material.AIR) {
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
			playSound(p);
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
			playSound(p);
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
			playSound(p);
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
			playSound(p);
			p.getInventory().setItemInMainHand(boots);
			p.getInventory().setBoots(item);
		}
		else {
			return;
		}
	}

	public void playSound(Player p){
		// get the helmet, chestplate, leggings, and boots
		ItemStack helm = p.getInventory().getHelmet();
		ItemStack chest = p.getInventory().getChestplate();
		ItemStack legs = p.getInventory().getLeggings();
		ItemStack boots = p.getInventory().getBoots();
		// get the held item
		ItemStack item = p.getInventory().getItemInMainHand();
		Material type = item.getType();
		if(type == Material.ELYTRA){
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1, 1);
		}
		else if(type == Material.TURTLE_HELMET){
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_TURTLE, 1, 1);
		}
		else if (type == Material.NETHERITE_HELMET
				|| type == Material.NETHERITE_CHESTPLATE
				|| type == Material.NETHERITE_LEGGINGS
				|| type == Material.NETHERITE_BOOTS) {
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1, 1);
		}
		else if (type == Material.DIAMOND_HELMET
				|| type == Material.DIAMOND_CHESTPLATE
				|| type == Material.DIAMOND_LEGGINGS
				|| type == Material.DIAMOND_BOOTS) {
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_DIAMOND, 1, 1);
		}
		else if (type == Material.IRON_HELMET
				|| type == Material.IRON_CHESTPLATE
				|| type == Material.IRON_LEGGINGS
				|| type == Material.IRON_BOOTS) {
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, 1, 1);
		}
		else if (type == Material.CHAINMAIL_HELMET
				|| type == Material.CHAINMAIL_CHESTPLATE
				|| type == Material.CHAINMAIL_LEGGINGS
				|| type == Material.CHAINMAIL_BOOTS) {
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 1);
		}
		else if(type == Material.GOLDEN_HELMET
				|| type == Material.GOLDEN_CHESTPLATE
				|| type == Material.GOLDEN_LEGGINGS
				|| type == Material.GOLDEN_BOOTS){
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, 1, 1);
		}
		else if(type == Material.LEATHER_HELMET
				|| type == Material.LEATHER_CHESTPLATE
				|| type == Material.LEATHER_LEGGINGS
				|| type == Material.LEATHER_BOOTS){
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
		}
	}
}
