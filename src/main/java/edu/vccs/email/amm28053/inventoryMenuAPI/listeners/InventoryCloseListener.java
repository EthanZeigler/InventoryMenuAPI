package edu.vccs.email.amm28053.inventoryMenuAPI.listeners;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import edu.vccs.email.amm28053.inventoryMenuAPI.InventoryMenuAPI;
import edu.vccs.email.amm28053.inventoryMenuAPI.menu.InventoryMenu;
import edu.vccs.email.amm28053.inventoryMenuAPI.util.Debug;

public class InventoryCloseListener implements Listener
{

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) 
	{	
		
		InventoryMenuAPI menuAPI = InventoryMenuAPI.getInstance();
		
		InventoryMenu menu = 
				menuAPI.getHandler().getMenu(event.getInventory().getName());
		
		if(menu != null) {
		
			HumanEntity lEntity = event.getPlayer();
		
			if(lEntity instanceof Player) {
				Player player = (Player)lEntity;
				Inventory inv = menu.getInventory();
				
				Debug.log("Menu being closed by: " + player.getName());
				
				if(inv.getViewers().isEmpty()) {
					menuAPI.getHandler().unregisterMenu(menu);
					Debug.log("Unregistering menu: " + menu.getName());
				}
			}
		}
	}
}
