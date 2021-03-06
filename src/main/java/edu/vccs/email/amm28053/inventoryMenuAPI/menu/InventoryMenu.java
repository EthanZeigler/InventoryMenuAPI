package edu.vccs.email.amm28053.inventoryMenuAPI.menu;

import edu.vccs.email.amm28053.inventoryMenuAPI.slot.AbstractSlot;
import net.minecraft.server.v1_7_R2.NBTTagCompound;
import net.minecraft.server.v1_7_R2.NBTTagList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A container for AbstractSlots.
 * 
 * @author Alex Marshall
 */
public class InventoryMenu {

    private final Plugin owner;
    private final String name;

    private List<AbstractSlot> slots;
    private Set<String> viewers;

    private Inventory inventory;

    /**
     * Creates an InventoryMenu with the name and owner specified.
     * 
     * @param name
     *            the name of the InventoryMenu
     * @param owner
     *            the plugin who created the InventoryMenu
     */
    InventoryMenu(String name, Plugin owner) {
	this.owner = owner;
	this.name = ChatColor.translateAlternateColorCodes('&', name);
	this.slots = new ArrayList<AbstractSlot>();
	this.viewers = new HashSet<String>();
    }

    /**
     * Creates an InventoryMenu with the name, owner, and slots specified.
     * 
     * @param name
     *            the name of the InventoryMenu
     * @param owner
     *            the plugin who created the InventoryMenu
     * @param slots
     *            the slots in the InventoryMenu
     */
    InventoryMenu(String name, Plugin owner, List<AbstractSlot> slots) {
	this(name, owner);
	this.slots = new ArrayList<AbstractSlot>(slots);
    }

    /**
     * Gets the plugin that owns this InventoryMenu.
     * 
     * @return the owner
     */
    public Plugin getOwner() {
	return this.owner;
    }

    /**
     * Gets the name of this InventoryMenu.
     * 
     * @return the name
     */
    public String getName() {
	return this.name;
    }

    /**
     * Package private method to get all the slots in the menu.
     * 
     * @return a list of all the slots
     */
    List<AbstractSlot> getSlots() {
	return new ArrayList<AbstractSlot>(this.slots);
    }

    /**
     * Gets the AbstractSlot in the specific slot number.
     * 
     * @param slotNum
     * @return
     */
    public AbstractSlot getSlot(int slotNum) {
	return (slots.size() <= slotNum) || (slotNum < 0) ? null : slots.get(slotNum);
    }

    /**
     * Gets the number of slots that are being filled in the InventoryMenu(does
     * not include empty slots).
     * 
     * @return number of slots
     */
    public int getNumSlots() {
	return slots.size();
    }

    /**
     * Gets the size of the inventory that is representing this InventoryMenu.
     * 
     * @return the size of inventory
     */
    public int getSize() {
	return slots.size() % 9 == 0 ? slots.size() : (slots.size() / 9 + 1) * 9;
    }

    /**
     * Adds a viewer to the InventoryMenu
     * 
     * @param player
     *            the player who is viewing the InventoryMenu.
     */
    public void addViewer(Player player) {
	viewers.add(player.getName());
    }

    /**
     * Removes a viewer from the InventoryMenu
     * 
     * @param player
     *            the player who is viewing the InventoryMenu.
     */
    public void removeViewer(Player player) {
	viewers.remove(player.getName());
    }

    /**
     * Gets the viewers of this InventoryMenu instance.
     * 
     * @return the players who are viewing this InventoryMenu.
     */
    public Set<Player> getViewers() {

	Set<Player> playerViewers = new HashSet<Player>();

	Iterator<String> iterator = viewers.iterator();
	while (iterator.hasNext()) {
	    String name = iterator.next();

	    Player player = Bukkit.getPlayerExact(name);

	    if (player == null) {
		iterator.remove();
		continue;
	    }
	    playerViewers.add(player);
	}
	return playerViewers;
    }

    /**
     * Returns the cached Inventory object of this InventoryMenu if one exists.
     * Otherwise, it builds and caches the built Inventory object.
     * 
     * @return the built inventory
     */
    public Inventory getInventory() {

	// If there isn't a cached inventory object, one is created.
	if (inventory == null) {

	    Inventory inventory = Bukkit.createInventory(null, getSize(),
		    ChatColor.translateAlternateColorCodes('&', getName()));

	    for (int i = 0; i < slots.size(); i++) {
		AbstractSlot slot = slots.get(i);

		if (slot != null) {
		    ItemStack item = new ItemStack(slot.getMaterial());

		    ItemMeta itemMeta = item.getItemMeta();
		    itemMeta.setDisplayName(slot.getId());

		    if (slot.getDescription() != null)
			itemMeta.setLore(slot.getDescription());

		    item.setItemMeta(itemMeta);

		    inventory.setItem(i, removeAttributes(item));
		}
	    }

	    this.inventory = inventory;
	}

	return inventory;
    }

    private static ItemStack removeAttributes(ItemStack i) {
	if (i == null) {
	    return i;
	}
	if (i.getType() == Material.BOOK_AND_QUILL) {
	    return i;
	}
	ItemStack item = i.clone();

	net.minecraft.server.v1_7_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
	NBTTagCompound tag;
	if (!nmsStack.hasTag()) {
	    tag = new NBTTagCompound();
	    nmsStack.setTag(tag);
	} else {
	    tag = nmsStack.getTag();
	}
	NBTTagList am = new NBTTagList();
	tag.set("AttributeModifiers", am);
	nmsStack.setTag(tag);
	return CraftItemStack.asCraftMirror(nmsStack);
    }

    @Override
    public boolean equals(Object other) {
	if (this == other)
	    return true;
	else if (other == null || this.getClass() != other.getClass())
	    return false;
	else
	    return name.equalsIgnoreCase(((InventoryMenu) other).getName());
    }

    @Override
    public int hashCode() {
	return name.hashCode();
    }
}
