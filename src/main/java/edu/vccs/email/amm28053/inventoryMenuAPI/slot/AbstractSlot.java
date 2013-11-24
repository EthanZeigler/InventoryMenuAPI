package edu.vccs.email.amm28053.inventoryMenuAPI.slot;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of an inventory slot.
 *
 * @author Alex Marshall (amhokies)
 */
public abstract class AbstractSlot {

    protected String id;
    protected Material mat;
    protected String description;

    private final int MAX_CHARS_PER_LINE = 32;

    /**
     * Creates a slot with the String id specified
     *  and the default Material representation.
     *
     *  @param id The String id of this slot.
     */
    public AbstractSlot(String id) {
        this.id = id;
        this.mat = Material.DIAMOND;
    }

    /**
     * Creates a slot with the Material representation passed in
     *  and the specified String id;
     *
     * @param id The String id of this slot.
     * @param mat the material representation of this slot
     */
    public AbstractSlot(String id, Material mat) {
        this.id = id;
        this.mat= mat;
    }

    /**
     * Method that will be executed when the slot is
     *  clicked on.
     */
    public abstract void execute();

    /**
     * Get the material representation of this slot.
     *
     * @return the material that represents this slot
     */
    public Material getMaterial() {
        return this.mat;
    }

    /**
     * Get the String id of this slot.
     *
     * @return the String identifier of this slot
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the description of this slot as
     *  a List of Strings.
     *
     * @return
     */
    public List<String> getDescription() {
        List<String> desc = new ArrayList<String>();

        StringBuilder line = new StringBuilder();
        int charsThisLine = 0;
        for(String s : description.split(" ")) {
            if(charsThisLine < MAX_CHARS_PER_LINE) {
                line.append(s).append(" ");
            } else {
                desc.add(line.toString());
                line = new StringBuilder(s);
                charsThisLine = 0;
            }
        }
        desc.add(line.toString());

        return desc;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        else if (other == null || this.getClass() != other.getClass())
            return false;
        else
            return id.equalsIgnoreCase(((AbstractSlot) other).getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
