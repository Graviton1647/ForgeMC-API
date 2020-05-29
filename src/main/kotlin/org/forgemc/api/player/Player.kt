package org.forgemc.api.player

import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material

import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.forgemc.api.ForgePlugin
import org.forgemc.api.ForgePlugin.Companion.playerMenu
import org.forgemc.api.ui.gui.PlayerMenu


fun Player.getMenu() : PlayerMenu {
    val menu : PlayerMenu
    return if(playerMenu.containsKey(player)) {
        playerMenu[player]!!
    } else {
        menu = PlayerMenu(player)
        playerMenu[player] = menu
        menu
    }
}

fun Player.message(vararg message : String) {

    message.forEach {
        sendMessage(ChatColor.translateAlternateColorCodes('&',it))
    }
}

/**
 * Sends a title and a subtitle message to the player.
 * @param title Title text
 * @param subtitle Subtitle text
 * @param fadeIn time in ticks for titles to fade in. Defaults to 10.
 * @param stay time in ticks for titles to stay. Defaults to 70.
 * @param fadeOut time in ticks for titles to fade out. Defaults to 20.
 */
fun Player.sendTitle(message : List<String>, sub : String = "", fadeIn : Int = 10, stay : Int = 70, fadeOut : Int = 20) {
    message.forEach {
        sendTitle(ChatColor.translateAlternateColorCodes('&',it),sub,fadeIn,stay,fadeOut)
    }
}

fun Player.sendTitle(message : String, sub : String = "", fadeIn : Int = 10, stay : Int = 70, fadeOut : Int = 20) {
    sendTitle(ChatColor.translateAlternateColorCodes('&',message),sub,fadeIn,stay,fadeOut)
}

fun Player.inRange(otherlocation : Location, distance : Double) : Boolean {
    return location.distance(otherlocation) <= distance
}

fun Player.teleport(
    location : Location,
    radius : Int,
    checks : Int,
    message: String = ChatColor.RED.toString() + "Couldn't find a safe place to teleport. Try again later.",
    notSafe : Array<Material> = arrayOf(Material.LAVA)
) {
    val findSafePlaceWorker: BukkitRunnable = FindSafeLocationRunnable(
        player,
        location,
        radius,
        checks,
        message,
        notSafe
    )
    findSafePlaceWorker.runTaskTimer(ForgePlugin.plugin, 1, 1)
}



