package kr.apo2073.skChzzk.utils

import kr.apo2073.skChzzk.SkChzzk
import org.bukkit.Bukkit
import xyz.r2turntrue.chzzk4j.Chzzk
import xyz.r2turntrue.chzzk4j.ChzzkBuilder
import xyz.r2turntrue.chzzk4j.chat.ChatEventListener
import xyz.r2turntrue.chzzk4j.chat.ChatMessage
import xyz.r2turntrue.chzzk4j.chat.DonationMessage
import xyz.r2turntrue.chzzk4j.chat.SubscriptionMessage
import xyz.r2turntrue.chzzk4j.chat.ChzzkChat
import xyz.r2turntrue.chzzk4j.types.channel.ChzzkChannel

object ChzzkChatManager {
    private var chat: ChzzkChat? = null
    private val chzzk: Chzzk = ChzzkBuilder().build()
    private var currentChannel: ChzzkChannel? = null

    fun connect(channelId: String): Boolean {
        try {
            currentChannel = chzzk.getChannel(channelId)

            if (chat != null) {
                disconnect()
            }

            chat = chzzk.chat(channelId)
                .withChatListener(object : ChatEventListener {
                    override fun onConnect(chat: ChzzkChat, isReconnecting: Boolean) {
                        if (!isReconnecting) {
                            chat.requestRecentChat(50)
                        }
                    }

                    override fun onError(ex: Exception) {
                        ex.printStackTrace()
                    }

                    override fun onChat(msg: ChatMessage) {
                        Bukkit.getPluginManager().callEvent(ChzzkChatEvent(msg))
                    }

                    override fun onDonationChat(msg: DonationMessage) {
                        Bukkit.getPluginManager().callEvent(ChzzkDonationEvent(msg))
                    }

                    override fun onSubscriptionChat(msg: SubscriptionMessage) {
                        Bukkit.getPluginManager().callEvent(ChzzkSubscriptionEvent(msg))
                    }
                })
                .build()

            chat?.connectBlocking()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            currentChannel = null
            return false
        }
    }

    fun disconnect() {
        try {
            chat?.closeBlocking()
            chat = null
            currentChannel = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getCurrentChannelInfo(): ChzzkChannel? = currentChannel
}