package edu.vccs.email.amm28053.inventoryMenuAPI.menu;

import edu.vccs.email.amm28053.inventoryMenuAPI.slot.AbstractSlot;

import org.bukkit.Bukkit;
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
		this.name = name;
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
	 * Gets the all the slots in the InventoryMenu
	 * 
	 * @return a set of all the slots
	 */
	public List<AbstractSlot> getSlots() {
		return new ArrayList<AbstractSlot>(this.slots);
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
	 * Adds a viewer to this InventoryMenu instance.
	 * 
	 * @param player the player who is viewing the InventoryMenu.
	 */
	public void addViewer(Player player) {
		viewers.add(player.getName());
	}
	
	/**
	 * Gets the viewers of this InventoryMenu instance.
	 * 
	 * @return the players who are viewing this InventoryMenu.
	 */
	public Set<Player> getViewers() {

		Set<Player> playerViewers = new HashSet<Player>();
		
		Iterator<String> iterator = viewers.iterator();
		while(iterator.hasNext()) {
			String name = iterator.next();
			
			Player player = Bukkit.getPlayerExact(name);
			
			if(player == null) {
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

			Inventory inventory = Bukkit.createInventory(null, getSize(), getName());

			for (int i = 0; i < slots.size(); i++) {
				AbstractSlot slot = slots.get(i);

				if (slot != null) {
					ItemStack item = new ItemStack(slot.getMaterial());
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setDisplayName(slot.getId());
					itemMeta.setLore(slot.getDescription());
					item.setItemMeta(itemMeta);
					inventory.setItem(i, item);
				}
			}

			this.inventory = inventory;
		}

		return inventory;
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
