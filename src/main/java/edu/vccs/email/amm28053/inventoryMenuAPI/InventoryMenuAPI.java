package edu.vccs.email.amm28053.inventoryMenuAPI;

import edu.vccs.email.amm28053.inventoryMenuAPI.menu.InventoryMenu;
import edu.vccs.email.amm28053.inventoryMenuAPI.menu.MenuHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The InventoryMenuAPI plugin main class.
 * @author Alex Marshall
 */
public class InventoryMenuAPI extends JavaPlugin {

    private MenuHandler handler;

    @Override
    public void onEnable() {
        handler = new MenuHandler();
    }

    @Override
    public void onDisable() {

    }

    /**
     * Static method to get the InventoryMenuAPI plugin instance.
     *
     * @return instance of InventoryMenuAPI plugin
     */
    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("InventoryMenuAPI");
    }

    /**
     * Register a menu to the MenuHandler.
     *
     * @param menu the menu to register
     * @param plugin the plugin that is registering the menu
     */
    public void registerMenu(InventoryMenu menu, Plugin plugin) {
        handler.registerMenu(menu);
    }

    /**
     * Deregister a menu from the MenuHandler.
     *
     * @param menu the InventoryMenu instance to deregister
     */
    public void deregisterMenu(InventoryMenu menu) {
        handler.deregisterMenu(menu.getName());
    }

    /**
     * Deregister a menu from the MenuHandler.
     *
     * @param menu the name of the InventoryMenu to deregister
     */
    public void deregisterMenu(String menu) {
        handler.deregisterMenu(menu);
    }
}
