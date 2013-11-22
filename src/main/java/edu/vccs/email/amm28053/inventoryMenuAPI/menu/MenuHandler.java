package edu.vccs.email.amm28053.inventoryMenuAPI.menu;

import org.apache.commons.lang.Validate;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Marshall
 */
public class MenuHandler {
    private Set<InventoryMenu> menus;

    public MenuHandler() {
        menus = new HashSet<InventoryMenu>();
    }

    public void registerMenu(InventoryMenu menu) {
        Validate.isTrue(menu.getSlots() > 0, "InventoryMenu owned by [" + menu.getOwner().getName() + "]" +
            " failed registration! Reason: Menu is empty!");
        menus.add(menu);
    }

    public boolean deregisterMenu(String menu) {
        for(InventoryMenu im : menus) {
            if(im.getName().equalsIgnoreCase(menu))
                return menus.remove(im);
        }
        return false;
    }
}
