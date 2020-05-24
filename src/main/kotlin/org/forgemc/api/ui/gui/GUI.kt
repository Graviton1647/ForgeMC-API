package org.forgemc.api.ui.gui

import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin


class GUI(
    mcServer : Server,
    plugin : JavaPlugin,
    name : String,
    private val amount : Int,
    private val items : List<InventorySlot> = emptyList(),
    private val fill : InventorySlot,
    onClick : (InventoryClickEvent) -> Unit = {}
) {

    val inv: Inventory  =  Bukkit.createInventory(null, amount, name)

    init {
        mcServer.pluginManager.registerEvents(ClickEvent(inv, name, onClick), plugin)
        if(!items.isNullOrEmpty()) {
            repeat(amount) {
                if(items.any { itm -> itm.slot == it }) {
                    inv.setItem(it, createGuiItem(items.find { itm -> itm.slot == it }!!))
                } else {
                    inv.setItem(it,createGuiItem(fill))
                }
            }
        }

    }

    private fun createGuiItem(data : InventorySlot): ItemStack {
        val item = data.material
        val meta = item.itemMeta
        meta.displayName = data.name
        if(data.lore.isNotEmpty()) {
            meta.lore = data.lore
            item.itemMeta = meta
        }
        return item
    }




}