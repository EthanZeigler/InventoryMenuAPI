package edu.vccs.email.amm28053.inventoryMenuAPI.menu;

import edu.vccs.email.amm28053.inventoryMenuAPI.slot.AbstractSlot;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Marshall
 */
public class MenuBuilder {

    private Plugin owner;
    private String name;
    private List<AbstractSlot> slots;

    /**
     * Creates a MenuBuilder that will be used to build
     *  an InventoryMenu instead of creating one directly.
     *
     * @param owner the plugin that creates the MenuBuilder
     */
    public MenuBuilder(Plugin owner) {
        this.owner = owner;
        this.slots = new ArrayList<AbstractSlot>();
    }

    /**
     * Creates a MenuBuilder that will be used to build
     *  an InventoryMenu instead of creating one directly.
     *
     * @param owner the plugin that creates the MenuBuilder
     * @param name the name of the InventoryMenu
     */
    public MenuBuilder(Plugin owner, String name) {
        this.owner = owner;
        this.name = name;
        this.slots = new ArrayList<AbstractSlot>();
    }

    /**
     * Creates a MenuBuilder using an already created InventoryMenu.
     * Can be useful for using a past InventoryMenu as a template
     * for a new one.
     *
     * @param menu the InventoryMenu whose attributes are being
     *             copied into the MenuBuilder
     */
    public MenuBuilder(InventoryMenu menu) {
        this.owner = menu.getOwner();
        this.name = menu.getName();
        this.slots = menu.getSlots();
    }

    /**
     * Gets the name of the InventoryMenu.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the InventoryMenu.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the plugin that created the MenuBuilder.
     *
     * @return the owner of the MenuBuilder
     */
    public Plugin getOwner() {
        return owner;
    }

    /**
     * Adds an AbstractSlot to the first available element
     *  in the InventoryMenu.
     * @param slot the AbstractSlot being added
     * @return true if successfully added
     */
    public boolean addSlot(AbstractSlot slot) {
        return slots.add(slot);
    }

    /**
     * Adds an AbstractSlot at the specified index.
     *
     * @param index the index of the inventory slot where this
     *              AbstractSlot is being added
     * @param slot the AbstractSlot being added
     */
    public void addSlot(int index, AbstractSlot slot) {
        slots.add(index, slot);
    }

    /**
     * Removes the AbstractSlot from the InventoryMenu, if
     *  it exists.
     *
     * @param slot the AbstractSlot to be removed
     * @return true if successfully removed
     */
    public boolean removeSlot(AbstractSlot slot) {
        return slots.remove(slot);
    }

    /**
     * Removes the AbstractSlot with the given name from the
     *  InventoryView, if there is a match.
     *
     * @param slotName the name of the AbstractSlot to be removed
     * @return true if successfully removed
     */
    public boolean removeSlot(String slotName) {
        for(AbstractSlot slot : slots) {
            if(slot.getId().equalsIgnoreCase(slotName))
                return slots.remove(slot);
        }
        return false;
    }

    /**
     * Removes the AbstractSlot at the specified index.
     *
     * @param slot the index of the AbstractSlot to be removed
     * @return true if successfully removed
     */
    public boolean removeSlot(int slot) {
        return slots.remove(slot) != null;
    }

    /**
     * Builds and returns an InventoryMenu object.
     *
     * @return the built InventoryMenu object
     */
    public InventoryMenu getMenu() {
        return new InventoryMenu(name, owner, slots);
    }
}
