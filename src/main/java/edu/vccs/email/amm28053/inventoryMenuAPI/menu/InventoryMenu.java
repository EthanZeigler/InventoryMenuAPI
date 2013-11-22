package edu.vccs.email.amm28053.inventoryMenuAPI.menu;

import edu.vccs.email.amm28053.inventoryMenuAPI.slot.AbstractSlot;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * A container for AbstractSlots.
 *
 * @author Alex Marshall
 */
public class InventoryMenu {

    private final Plugin owner;
    private final String name;
    private final List<AbstractSlot> slots;

    public InventoryMenu(String name, Plugin owner) {
        this.owner = owner;
        this.name = name;
        this.slots = new ArrayList<AbstractSlot>();
    }

    public Plugin getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }

    public int getSlots() {
        return slots.size();
    }

    public int getSize() {
        return slots.size() % 9 == 0 ? slots.size() : (slots.size() / 9 + 1) * 9;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        else if (other == null || this.getClass() != other.getClass())
            return false;
        else
            return name.equalsIgnoreCase(((InventoryMenu)other).getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
