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
                    override fun onError(ex: Exception) {
                        ex.printStackTrace()
                    }

                    override fun onChat(msg: ChatMessage, chat: ChzzkChat) {
                        Bukkit.getScheduler() .runTask(SkChzzk.instance, Runnable {
                            try {
                                val event=ChzzkChatEvent(msg, chat)
                                Bukkit.getPluginManager().callEvent(event)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        })
                    }

                    override fun onDonationChat(msg: DonationMessage, chat: ChzzkChat) {
                        Bukkit.getScheduler() .runTask(SkChzzk.instance, Runnable {
                            try {
                                val event=ChzzkDonationEvent(msg, chat)
                                Bukkit.getPluginManager().callEvent(event)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        })
                    }

                    override fun onSubscriptionChat(msg: SubscriptionMessage, chat: ChzzkChat) {
                        Bukkit.getScheduler() .runTask(SkChzzk.instance, Runnable {
                            try {
                                val event=ChzzkSubscriptionEvent(msg, chat)
                                Bukkit.getPluginManager().callEvent(event)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        })
                    }
                }).build()

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

    fun getChannelName(id:String):String= chzzk.getChannel(id)?.channelName ?: "알 수 없음"
    fun getChannelFollower(id:String):String= (chzzk.getChannel(id)?.followerCount ?: "0").toString()
    fun getCurrentChannelInfo(): ChzzkChannel? = currentChannel
    fun getChzzk(): Chzzk= chzzk
}