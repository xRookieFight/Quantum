package com.quantum.player

import java.util.concurrent.ConcurrentHashMap

/**
 * Player management utils
 * @author xRookieFight
 * @since 03/02/2026
 */
class PlayerManager {

    private val playersByName = ConcurrentHashMap<String, Player>()

    fun addPlayer(player: Player) {
        playersByName[player.name.lowercase()] = player
    }

    fun removePlayer(player: Player) {
        playersByName.remove(player.name.lowercase())
    }

    fun getPlayerByName(name: String): Player?
    = playersByName[name.lowercase()]
}
