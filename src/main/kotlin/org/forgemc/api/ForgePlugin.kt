package org.forgemc.api

import com.connorlinfoot.bountifulapi.BountifulAPI
import mu.KotlinLogging
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.forgemc.api.commands.CommandLoader
import org.forgemc.api.database.DatabaseManager
import org.forgemc.api.events.EventLoader
import kotlin.system.measureTimeMillis

abstract class ForgePlugin : JavaPlugin() {

    companion object {
        lateinit var plugin : JavaPlugin

        val logger = KotlinLogging.logger {  }

    }

    override fun onEnable() {
        plugin = this
        logger.info { "Starting ${this.name}" }
        BountifulAPI.nmsver = Bukkit.getServer().javaClass.getPackage().name
        BountifulAPI.nmsver = BountifulAPI.nmsver.substring(BountifulAPI.nmsver.lastIndexOf(".") + 1)

        if (BountifulAPI.nmsver.equals("v1_8_R1", ignoreCase = true) || BountifulAPI.nmsver.equals("v1_7_", ignoreCase = true)) { // Not sure if 1_7 works for the protocol hack?
            BountifulAPI.useOldMethods = true
        }
        val time =  measureTimeMillis {
            DatabaseManager.init("$dataFolder//data.db")
            EventLoader.load(this)
            CommandLoader.load(this)
            start()
        }
        logger.info { "${this.name} Started up in [${time.toDouble() / 1000.0}] seconds." }
    }

    override fun onDisable() {
        stop()
        DatabaseManager.close()
        logger.info { "Stopped ${this.name}" }
    }


    /**
     * Called when the plugin is enabled
     */
    abstract fun start()

    /**
     * Called when the plugin is disabled
     */
    abstract fun stop()

}