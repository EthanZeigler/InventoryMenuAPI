package edu.vccs.email.amm28053.inventoryMenuAPI.slot;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
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
    protected boolean closeOnClick;

    /**
     * Creates a slot with the String id specified and the default Material
     * representation.
     * 
     * @param id
     *            The String id of this slot.
     */
    public AbstractSlot(String id) {
	this.id = ChatColor.translateAlternateColorCodes('&', id);
	this.mat = Material.DIAMOND;
	this.closeOnClick = true;
    }

    /**
     * Creates a slot with the Material representation passed in and the
     * specified String id;
     * 
     * @param id
     *            The String id of this slot.
     * @param mat
     *            the material representation of this slot
     */
    public AbstractSlot(String id, Material mat) {
	this(id);
	this.mat = mat;
    }

    /**
     * Method that will be executed when the slot is clicked on.
     */
    public abstract void execute(Player player);

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
     * Get the description of this slot as a List of Strings.
     * 
     * @return the description of this slot as a list of strings
     */
    public List<String> getDescription() {
	return description == null ? null : Arrays.asList(description.split("_"));
    }

    /**
     * Set this AbstractSlot's description.
     * 
     * @param desc
     *            the description of this slot
     * @return the AbstractSlot object
     */
    public AbstractSlot setDescription(String desc) {
	this.description = ChatColor.translateAlternateColorCodes('&', desc);
	return this;
    }

    /**
     * Returns whether the menu closes automatically when this slot is clicked.
     * 
     * @return true if the menu closes when a slot is clicked.
     */
    public boolean closeOnClick() {
	return this.closeOnClick;
    }

    /**
     * Sets whether the menu closes automatically when this slot is clicked.
     * 
     * @param closeOnClick
     * @return the AbstractSlot object
     */
    public AbstractSlot setCloseOnClick(boolean closeOnClick) {
	this.closeOnClick = closeOnClick;
	return this;
    }
}
