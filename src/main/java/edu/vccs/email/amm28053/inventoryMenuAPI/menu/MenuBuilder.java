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

    public MenuBuilder(Plugin owner) {
        this.owner = owner;
        this.slots = new ArrayList<AbstractSlot>();
    }

    public MenuBuilder(Plugin owner, String name) {
        this.owner = owner;
        this.name = name;
        this.slots = new ArrayList<AbstractSlot>();
    }

    public MenuBuilder(InventoryMenu menu) {
        this.owner = menu.getOwner();
        this.name = menu.getName();
        this.slots = menu.getSlots();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plugin getOwner() {
        return owner;
    }

    public boolean addSlot(AbstractSlot slot) {
        return slots.add(slot);
    }

    public void addSlot(int index, AbstractSlot slot) {
        slots.add(index, slot);
    }

    public boolean removeSlot(AbstractSlot slot) {
        return slots.remove(slot);
    }

    public boolean removeSlot(String slotName) {
        for(AbstractSlot slot : slots) {
            if(slot.getId().equalsIgnoreCase(slotName))
                return slots.remove(slot);
        }
        return false;
    }

    public boolean removeSlot(int slot) {
        return slots.remove(slot) != null;
    }

    public InventoryMenu getMenu() {
        return new InventoryMenu(name, owner, slots);
    }
}
