package com.quantum.network

import com.quantum.Server
import com.quantum.network.handler.PreLoginPacketHandler
import com.quantum.network.version.BedrockVersion
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioDatagramChannel
import org.cloudburstmc.netty.channel.raknet.RakChannelFactory
import org.cloudburstmc.netty.channel.raknet.config.RakChannelOption
import org.cloudburstmc.protocol.bedrock.BedrockPeer
import org.cloudburstmc.protocol.bedrock.BedrockPong
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockChannelInitializer
import java.net.InetSocketAddress
import java.util.UUID

/**
 * Head RakNet network class
 * @author xRookieFight
 * @since 03/02/2026
 */
class RakNetServer {

    private val advertisement = BedrockPong()
        .edition("MCPE")
        .gameType("Survival")
        .version(BedrockVersion.LATEST.name)
        .protocolVersion(BedrockVersion.LATEST.protocol)
        .motd("Quantum Server")
        .playerCount(0)
        .maximumPlayerCount(20)
        .serverId(UUID.randomUUID().mostSignificantBits)
        .subMotd("quantum.org")
        .nintendoLimited(false)
        .ipv4Port(19132)
        .ipv6Port(19132)

    var server: Channel = ServerBootstrap()
        .group(NioEventLoopGroup())
        .channelFactory(RakChannelFactory.server(NioDatagramChannel::class.java))
        .option(RakChannelOption.RAK_ADVERTISEMENT, advertisement.toByteBuf())
        .childHandler(object : BedrockChannelInitializer<Session>() {
            override fun createSession0(peer: BedrockPeer, subClientId: Int): Session
            = Session(peer, subClientId, Server.instance)

            override fun initSession(session: Session) {
                session.packetHandler = PreLoginPacketHandler().attach(session, Server.instance)
            }

        })
        .bind(InetSocketAddress("127.0.0.1", 19132))
        .awaitUninterruptibly()
        .channel()

    fun shutdown() {
        server.disconnect()
    }
}