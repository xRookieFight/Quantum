package com.quantum.network.handler

import org.cloudburstmc.protocol.bedrock.codec.v898.Bedrock_v898
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm
import org.cloudburstmc.protocol.bedrock.packet.NetworkSettingsPacket
import org.cloudburstmc.protocol.bedrock.packet.RequestNetworkSettingsPacket
import org.cloudburstmc.protocol.common.PacketSignal

class PreLoginPacketHandler : PacketHandler() {

    override fun handle(packet: RequestNetworkSettingsPacket): PacketSignal {
        val packet = NetworkSettingsPacket().apply {
            compressionThreshold = 0
            compressionAlgorithm = PacketCompressionAlgorithm.ZLIB
        }

        session.codec = Bedrock_v898.CODEC

        session.sendPacketImmediately(packet)
        session.setCompression(PacketCompressionAlgorithm.ZLIB)

        session.packetHandler = LoginPacketHandler().attach(session, session.server)
        return PacketSignal.HANDLED
    }
}