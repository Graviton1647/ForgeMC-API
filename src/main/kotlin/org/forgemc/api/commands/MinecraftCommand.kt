package org.forgemc.api.commands

annotation class MinecraftCommand(
    val name : String,
    val description : String,
    val usageMessage : String,
    val aliasses : Array<String>
)