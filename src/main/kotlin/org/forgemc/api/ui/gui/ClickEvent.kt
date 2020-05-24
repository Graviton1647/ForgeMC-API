package org.forgemc.api.ui.gui

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory


class ClickEvent(private val guiInventory : Inventory, private val name : String, val action : (InventoryClickEvent) -> Unit) : Listener {
    @EventHandler
    fun clickEvent(event : InventoryClickEvent) {
        if(guiInventory.viewers.contains(event.whoClicked)) {
            action(event)
            event.isCancelled = true
        }
    }

    @EventHandler
    fun clickEvent1(event : InventoryClickEvent) {
        if(guiInventory.title.contains(name)) {
            action(event)
            event.isCancelled = true
        }
    }

}
