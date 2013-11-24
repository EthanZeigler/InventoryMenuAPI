package edu.vccs.email.amm28053.inventoryMenuAPI.menu;

import org.apache.commons.lang.Validate;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Marshall
 */
public class MenuHandler {
    private Set<InventoryMenu> menus;

    /**
     * Creates a MenuHandler to store InventoryMenus that
     *  are registered and waiting for input.
     */
    public MenuHandler() {
        menus = new HashSet<InventoryMenu>();
    }

    /**
     * Registers an InventoryMenu.
     *
     * @param menu the InventoryMenu to be registered
     * @return true if successfully registered
     */
    public boolean registerMenu(InventoryMenu menu) {
        Validate.isTrue(menu.getNumSlots() > 0, "InventoryMenu owned by [" + menu.getOwner().getName() + "]" +
            " failed registration! Reason: Menu is empty!");
        return menus.add(menu);
    }
}
