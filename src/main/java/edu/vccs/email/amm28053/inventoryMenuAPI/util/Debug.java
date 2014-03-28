package edu.vccs.email.amm28053.inventoryMenuAPI.util;

import org.bukkit.plugin.Plugin;

import edu.vccs.email.amm28053.inventoryMenuAPI.InventoryMenuAPI;

public class Debug {
	
	/** 
	 * Prevent instances of this class being created
	 */
	private Debug(){}
	
	/**
	 * Logs a debug message if debugging is turned on.
	 * @param message the message to be logged.
	 */
	public static void log(String message) {
		
		Plugin plugin = InventoryMenuAPI.getInstance();
		
		if(plugin.getConfig().getBoolean("debug")) {
			plugin.getLogger().info("[DEBUG] " + message);
		}
	}
}
