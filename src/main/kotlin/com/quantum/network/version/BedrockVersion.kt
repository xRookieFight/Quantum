package com.quantum.network.version

/**
 * Represents supported Minecraft Bedrock Edition versions
 * and their corresponding protocol numbers.
 *
 * @property name Version network name
 * @property protocol Protocol version
 *
 * @author xRookieFight
 * @since 03/02/2026
 */
data class BedrockVersion(
    val name: String,
    val protocol: Int
) {

    companion object {
        val V1_21_0   = BedrockVersion("1.21.0", 685)
        val V1_21_2   = BedrockVersion("1.21.2", 686)
        val V1_21_20  = BedrockVersion("1.21.20", 712)
        val V1_21_30  = BedrockVersion("1.21.30", 729)
        val V1_21_40  = BedrockVersion("1.21.40", 748)
        val V1_21_50  = BedrockVersion("1.21.50", 766)
        val V1_21_60  = BedrockVersion("1.21.60", 776)
        val V1_21_70  = BedrockVersion("1.21.70", 786)
        val V1_21_80  = BedrockVersion("1.21.80", 800)
        val V1_21_90  = BedrockVersion("1.21.90", 817)
        val V1_21_93  = BedrockVersion("1.21.93", 819)
        val V1_21_100 = BedrockVersion("1.21.100", 827)
        val V1_21_110 = BedrockVersion("1.21.110", 843)
        val V1_21_111 = BedrockVersion("1.21.111", 844)
        val V1_21_120 = BedrockVersion("1.21.120", 859)
        val V1_21_124 = BedrockVersion("1.21.124", 860)
        val V1_21_130 = BedrockVersion("1.21.130", 898)

        val ALL = listOf(
            V1_21_0, V1_21_2, V1_21_20, V1_21_30, V1_21_40,
            V1_21_50, V1_21_60, V1_21_70, V1_21_80, V1_21_90,
            V1_21_93, V1_21_100, V1_21_110, V1_21_111,
            V1_21_120, V1_21_124, V1_21_130
        )

        val LATEST = V1_21_130

        fun fromProtocol(protocol: Int): BedrockVersion?
        = ALL.firstOrNull { it.protocol == protocol }

        fun fromName(name: String): BedrockVersion?
        = ALL.firstOrNull { it.name == name }
    }

    fun isBefore(version: BedrockVersion): Boolean
    = protocol < version.protocol

    fun isBeforeOrEqual(version: BedrockVersion): Boolean
    = protocol <= version.protocol

    fun isAfter(version: BedrockVersion): Boolean
    = protocol > version.protocol

    fun isAfterOrEqual(version: BedrockVersion): Boolean
    = protocol >= version.protocol
}