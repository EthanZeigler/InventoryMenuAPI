package edu.vccs.email.amm28053.inventoryMenuAPI;

import java.util.Collection;

import edu.vccs.email.amm28053.inventoryMenuAPI.listeners.InventoryClickListener;
import edu.vccs.email.amm28053.inventoryMenuAPI.listeners.InventoryCloseListener;
import edu.vccs.email.amm28053.inventoryMenuAPI.menu.InventoryMenu;
import edu.vccs.email.amm28053.inventoryMenuAPI.menu.MenuHandler;
import edu.vccs.email.amm28053.inventoryMenuAPI.util.Debug;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The InventoryMenuAPI plugin main class.
 * 
 * @author Alex Marshall
 */
public class InventoryMenuAPI extends JavaPlugin {

    private MenuHandler handler;

    @Override
    public void onEnable() {
	handler = new MenuHandler();

	saveDefaultConfig();

	if (getConfig().getBoolean("debug"))
	    Debug.log("Debugging is enabled. To disable, set the debug value in the "
		    + "InventoryMenuAPI config to false.");

	getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
	getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
    }

    @Override
    public void onDisable() {

    }

    /**
     * Static method to get the InventoryMenuAPI plugin instance.
     * 
     * @return instance of InventoryMenuAPI plugin
     */
    public static InventoryMenuAPI getInstance() {
	return (InventoryMenuAPI) Bukkit.getPluginManager().getPlugin("InventoryMenuAPI");
    }

    /**
     * Show an InventoryMenu to a Player.
     * 
     * @param player
     *            the player to show the InventoryMenu to
     */
    public void showInventoryMenu(Player player, InventoryMenu menu) {

	if (handler.registerMenu(menu))
	    Debug.log("Registering menu: " + menu.getName());

	menu.addViewer(player);

	player.openInventory(menu.getInventory());

	Debug.log("Showing InventoryMenu " + menu.getName() + " to " + player.getName() + ".");
    }

    /**
     * Show an InventoryMenu to a given set of Players.
     * 
     * @param players
     *            the players to show the InventoryMenu to
     */
    public void showInventoryMenu(Collection<Player> players, InventoryMenu menu) {
	for (Player player : players)
	    showInventoryMenu(player, menu);
    }

    public MenuHandler getHandler() {
	return handler;
    }
}
