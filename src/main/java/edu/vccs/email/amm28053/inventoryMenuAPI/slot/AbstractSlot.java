package edu.vccs.email.amm28053.inventoryMenuAPI.slot;

import org.bukkit.Material;

/**
 * @author Alex Marshall
 */
public abstract class AbstractSlot {

    private int id;
    private Material mat;

    /**
     * Creates a slot with the id passed in and default material.
     * @param id the id to assign to this slot.
     */
    public AbstractSlot(int id) {
        this.id = id;
        this.mat = Material.BOOK;
    }

    /**
     * Creates a slot with the id and material passed in.
     * @param id the id to assign to this slot
     * @param mat the material representation of this slot
     */
    public AbstractSlot(int id, Material mat) {
        this(id);
        this.mat= mat;
    }

    /**
     * Method that will be executed when the slot is
     * clicked on.
     */
    public abstract void execute();

    /**
     * Accessor method for the slot's id.
     * @return the id of this slot
     */
    public int getId() { return this.id; }

    /**
     * Accessor method for the material representation of this slot.
     * @return the material that represents this slot
     */
    public Material getMaterial() { return this.mat; }
}
