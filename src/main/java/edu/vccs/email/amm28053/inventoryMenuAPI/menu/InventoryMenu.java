package edu.vccs.email.amm28053.inventoryMenuAPI.menu;

import edu.vccs.email.amm28053.inventoryMenuAPI.slot.AbstractSlot;

import java.util.LinkedList;
import java.util.List;

/**
 * A container for AbstractSlots.
 * @author Alex Marshall
 */
public class InventoryMenu {

    private final String name;
    private List<AbstractSlot> slots;

    public InventoryMenu(String name) {
        this.name = name;
        this.slots = new LinkedList<AbstractSlot>();
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return slots.size() % 9 == 0 ? slots.size() : (slots.size() / 9 + 1) * 9;
    }
}
