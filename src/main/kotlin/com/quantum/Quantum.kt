package com.quantum

import org.apache.logging.log4j.LogManager

/**
 * The launcher class of Quantum
 * @author xRookieFight
 * @since 03/02/2026
 */
object Quantum {

    val name = "Quantum"
    val version = "1.0.0-alpha"

    @JvmStatic
    fun main(args: Array<String>){
        val logger = LogManager.getLogger("Main Thread")
        try {
            Server(logger)
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
    }
}