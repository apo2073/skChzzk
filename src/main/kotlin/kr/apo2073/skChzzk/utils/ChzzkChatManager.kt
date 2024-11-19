package kr.apo2073.skChzzk.utils

import kr.apo2073.skChzzk.SkChzzk
import org.bukkit.Bukkit
import xyz.r2turntrue.chzzk4j.Chzzk
import xyz.r2turntrue.chzzk4j.ChzzkBuilder
import xyz.r2turntrue.chzzk4j.chat.*
import xyz.r2turntrue.chzzk4j.types.channel.ChzzkChannel
import java.util.*

object ChzzkChatManager {
    private var chat: ChzzkChat? = null
    private val chzzk: Chzzk = ChzzkBuilder().build()
    private var currentChannel: ChzzkChannel? = null
    private val playerChannels = mutableMapOf<UUID, ChzzkChannel>()

    fun connect(channelId: String, uuid: UUID): Boolean {
        try {
            val channel = chzzk.getChannel(channelId)
            currentChannel = channel
            playerChannels[uuid] = channel

            if (chat != null) disconnect(uuid)

            chat = chzzk.chat(channelId)
                .withChatListener(object : ChatEventListener {
                    override fun onError(ex: Exception) { ex.printStackTrace() }

                    override fun onChat(msg: ChatMessage, chat: ChzzkChat) {
                        Bukkit.getScheduler() .runTask(SkChzzk.instance, Runnable {
                            val event=ChzzkChatEvent(msg, chat)
                            Bukkit.getPluginManager().callEvent(event)
                        })
                    }

                    override fun onDonationChat(msg: DonationMessage, chat: ChzzkChat) {
                        Bukkit.getScheduler() .runTask(SkChzzk.instance, Runnable {
                            val event=ChzzkDonationEvent(msg, chat)
                            Bukkit.getPluginManager().callEvent(event)
                        })
                    }

                    override fun onSubscriptionChat(msg: SubscriptionMessage, chat: ChzzkChat) {
                        Bukkit.getScheduler() .runTask(SkChzzk.instance, Runnable {
                            val event=ChzzkSubscriptionEvent(msg, chat)
                            Bukkit.getPluginManager().callEvent(event)
                        })
                    }
                }).build()

            chat?.connectBlocking()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            currentChannel = null
            playerChannels.remove(uuid)
            return false
        }
    }

    fun disconnect(uuid: UUID? = null) {
        try {
            chat?.closeBlocking()
            chat = null
            playerChannels.remove(uuid) ?: playerChannels.clear()
            currentChannel = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun disconnectAll() {
        try {
            playerChannels.keys.forEach { uuid ->
                val player = Bukkit.getPlayer(uuid) ?: return@forEach
                chzzk.chat(playerChannels[player.uniqueId]?.channelId).build().closeBlocking()
            }

            chat?.closeBlocking()
            chat = null
            currentChannel = null

            playerChannels.clear()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getChannelName(id: String): String {
        return try {
            chzzk.getChannel(id)?.channelName ?: "알 수 없음"
        } catch (e: Exception) {
            "알 수 없음"
        }
    }

    fun getChannelFollower(id: String): String {
        return try {
            (chzzk.getChannel(id)?.followerCount ?: 0).toString()
        } catch (e: Exception) {
            "0"
        }
    }

    fun getChannelPlayer(id: String): String {
        return Bukkit.getOnlinePlayers()
            .firstOrNull { playerChannels[it.uniqueId]?.channelId == id }
            ?.name ?: "NONE"
    }

    fun getCurrentChannelInfo(): ChzzkChannel? = currentChannel
    fun getPlayerChannel(uuid: UUID): ChzzkChannel? = playerChannels[uuid]
    fun getChzzk(): Chzzk = chzzk
}