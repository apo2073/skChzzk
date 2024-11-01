package kr.apo2073.skChzzk.utils

import kr.apo2073.lib.Plugins.txt
import kr.apo2073.skChzzk.SkChzzk
import org.bukkit.Bukkit
import xyz.r2turntrue.chzzk4j.Chzzk
import xyz.r2turntrue.chzzk4j.ChzzkBuilder
import xyz.r2turntrue.chzzk4j.chat.*

object ChzzkChatManager {
    private var chat: ChzzkChat? = null
    private val chzzk: Chzzk = ChzzkBuilder().build()
    val instance: ChzzkChatManager = this

    fun connect(channelId: String) {
        if (chat != null) {
            disconnect()
        }

        try {
            chat = chzzk.chat(channelId)
                .withChatListener(object : ChatEventListener {
                    override fun onConnect(chat: ChzzkChat, isReconnecting: Boolean) {
                        if (!isReconnecting) {
                            chat.requestRecentChat(50)
                        }
                        Bukkit.getScheduler().runTask(
                            SkChzzk.instance,
                            Runnable {
                                Bukkit.broadcastMessage("§a치지직 채팅이 연결되었습니다!")
                            }
                        )
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun disconnect() {
        try {
            chat?.closeBlocking()
            chat = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}