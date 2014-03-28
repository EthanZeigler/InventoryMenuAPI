package edu.vccs.email.amm28053.inventoryMenuAPI.menu;

import org.apache.commons.lang.Validate;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Marshall
 */
public class MenuHandler
{
    private Set<InventoryMenu> openMenus;

    /**
     * Creates a MenuHandler to store InventoryMenus that
     *  are registered and waiting for input.
     */
    public MenuHandler() 
    {
        openMenus = new HashSet<InventoryMenu>();
    }

    /**
     * Registers an InventoryMenu that is currently open.
     *
     * @param menu the InventoryMenu to be registered
     * @return true if successfully registered
     */
    public boolean registerMenu(InventoryMenu menu) 
    {
        Validate.isTrue(menu.getNumSlots() > 0, "InventoryMenu owned by [" + menu.getOwner().getName() + "]" +
            " failed registration! Reason: Menu is empty!");
        return openMenus.add(menu);
    }
    
    /**
     * Unregisters an InventoryMenu after all instances are closed.
     * 
     * @param menu the InventoryMenu being unregistered
     * @return true if successfully unregistered
     */
    public boolean unregisterMenu(InventoryMenu menu) 
    {
    	return openMenus.remove(menu);
    }
    
    /**
     * Returns an InventoryMenu with the name passed in.
     * If there are no matches, returns null.
     * 
     * @param name the title of the InventoryMenu
     * @return matched InventoryMenu or null if none found
     */
    public InventoryMenu getMenu(String name) 
    {
    	for(InventoryMenu menu : openMenus) {
    		if(menu.getName().equals(name)) {
    			return menu;
    		}
    	}
    	return null;
    }
}
