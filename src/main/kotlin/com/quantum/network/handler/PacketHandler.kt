package com.quantum.network.handler

import com.quantum.Server
import com.quantum.network.Session
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketHandler

abstract class PacketHandler : BedrockPacketHandler {

    protected lateinit var session: Session
        private set

    protected lateinit var server: Server
        private set

    fun attach(session: Session, server: Server): PacketHandler {
        this.session = session
        this.server = server

        return this
    }

    override fun onDisconnect(reason: CharSequence) {
        val player = session.player

        if (player !== null) {
            server.logger.info("{} disconnected. Reason: {}", player.name, reason)
            player.remove()
        }
    }
}