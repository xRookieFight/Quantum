package com.quantum.network.handler

import org.cloudburstmc.protocol.bedrock.packet.ResourcePackClientResponsePacket
import org.cloudburstmc.protocol.common.PacketSignal

class ResourcePackPacketHandler : PacketHandler() {

    override fun handle(packet: ResourcePackClientResponsePacket): PacketSignal {
        server.logger.info(packet.packetType.toString())
        return PacketSignal.HANDLED
    }
}
