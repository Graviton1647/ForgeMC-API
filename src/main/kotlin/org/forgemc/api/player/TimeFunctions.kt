package org.forgemc.api.player

import java.util.concurrent.TimeUnit

fun toSeconds(mins : Int) {
    TimeUnit.MINUTES.toSeconds(mins.toLong())
}
