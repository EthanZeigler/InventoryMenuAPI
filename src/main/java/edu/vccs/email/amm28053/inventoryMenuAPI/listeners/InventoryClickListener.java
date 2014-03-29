package edu.vccs.email.amm28053.inventoryMenuAPI.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

import edu.vccs.email.amm28053.inventoryMenuAPI.InventoryMenuAPI;
import edu.vccs.email.amm28053.inventoryMenuAPI.menu.InventoryMenu;
import edu.vccs.email.amm28053.inventoryMenuAPI.slot.AbstractSlot;
import edu.vccs.email.amm28053.inventoryMenuAPI.util.Debug;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		InventoryMenuAPI menuAPI = InventoryMenuAPI.getInstance();
		InventoryMenu menu = menuAPI.getHandler().getMenu(event.getInventory().getName());
		
		if(menu != null) {
			HumanEntity hEntity = event.getWhoClicked();
			
			if(hEntity instanceof Player) {
				final Player player = (Player)hEntity;
				int slot = event.getRawSlot();
				
				AbstractSlot aSlot = menu.getSlot(slot);
				
				if(aSlot != null) {
					aSlot.execute(player);
					Debug.log(player.getName() + " clicked slot number " + slot);
					
					if(aSlot.closeOnClick()) {
						Bukkit.getScheduler().runTask(menuAPI, new BukkitRunnable() {
							public void run() {
								player.closeInventory();
							}
						});
					}
				}
			}
			event.setCancelled(true);
		}
	}
}
