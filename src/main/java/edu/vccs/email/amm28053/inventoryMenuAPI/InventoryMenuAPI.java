package edu.vccs.email.amm28053.inventoryMenuAPI;

import edu.vccs.email.amm28053.inventoryMenuAPI.menu.InventoryMenu;
import edu.vccs.email.amm28053.inventoryMenuAPI.menu.MenuHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

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
     * @param player the player to show the InventoryMenu to
     */
    public void showInventoryMenu(Player player, InventoryMenu menu) {
        handler.registerMenu(menu);
        player.openInventory(menu.getInventory());
    }

    /**
     * Show an InventoryMenu to a given set of Players.
     *
     * @param players the players to show the InventoryMenu to
     */
    public void showInventoryMenu(Set<Player> players, InventoryMenu menu) {
        for(Player player : players)
            showInventoryMenu(player, menu);
    }
}
