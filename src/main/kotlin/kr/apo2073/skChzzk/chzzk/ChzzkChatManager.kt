package kr.apo2073.skChzzk.chzzk

import kr.apo2073.skChzzk.SkChzzk
import org.bukkit.Bukkit
import xyz.r2turntrue.chzzk4j.ChzzkClient
import xyz.r2turntrue.chzzk4j.ChzzkClientBuilder
import xyz.r2turntrue.chzzk4j.chat.ChzzkChat
import xyz.r2turntrue.chzzk4j.chat.ChzzkChatBuilder
import xyz.r2turntrue.chzzk4j.chat.event.ChatMessageEvent
import xyz.r2turntrue.chzzk4j.chat.event.MissionDonationEvent
import xyz.r2turntrue.chzzk4j.chat.event.NormalDonationEvent
import xyz.r2turntrue.chzzk4j.chat.event.SubscriptionMessageEvent
import xyz.r2turntrue.chzzk4j.types.channel.ChzzkChannel
import java.util.*

object ChzzkChatManager {
    private lateinit var client: ChzzkClient
    private var chat: ChzzkChat? = null
    private var currentChannel: ChzzkChannel? = null
    private val playerChannels = mutableMapOf<UUID, ChzzkChat?>()
    private val data= SkChzzk.chzzkData

    fun connect(channelId: String, uuid: UUID): Boolean {
        try {
            val key=data.getClientKey() ?: return false
            client= ChzzkClientBuilder(key.id, key.secret)
                .apply {
                    data.getAdapter().forEach {
                        withLoginAdapter(it)
                    }
                }
                .build()
            val channel = client.fetchChannel(channelId)
            currentChannel = channel

            if (chat != null) disconnect(uuid)
            chat = ChzzkChatBuilder(client, channelId).build() ?: return false
            chat?.on(ChatMessageEvent::class.java) { evt->
                Bukkit.getScheduler() .runTask(SkChzzk.plugin, Runnable {
                    val event= ChzzkChatEvent(evt.message, evt.chat)
                    val isSuc=event.callEvent()
                    if (!isSuc) throw Exception("치지직 채팅 이벤트를 처리하던 중 오류가 발생했습니다.")
                })
            }
            chat?.on(NormalDonationEvent::class.java) { evt->
                Bukkit.getScheduler() .runTask(SkChzzk.plugin, Runnable {
                    val event= ChzzkDonationEvent(evt.message, evt.chat)
                    val isSuc=event.callEvent()
                    if (!isSuc) throw Exception("치지직 후원 이벤트를 처리하던 중 오류가 발생했습니다.")
//                    println("chzzk event called"+evt.message.payAmount)
                })
            }
            chat?.on(MissionDonationEvent::class.java) { evt ->
                Bukkit.getScheduler() .runTask(SkChzzk.plugin, Runnable {
//                    println(evt.message.payAmount) // done??????
                    val event= ChzzkMissionDonationEvent(evt.message, evt.chat)
                    val isSuc=event.callEvent()
                    if (!isSuc) throw Exception("치지직 미션 후원 이벤트를 처리하던 중 오류가 발생했습니다.")
                })
            }
            chat?.on(SubscriptionMessageEvent::class.java) { evt->
                Bukkit.getScheduler() .runTask(SkChzzk.plugin, Runnable {
                    val event= ChzzkSubscriptionEvent(evt.message, evt.chat)
                    val isSuc=event.callEvent()
                    if (!isSuc) throw Exception("치지직 구독 이벤트를 처리하던 중 오류가 발생했습니다.")
                })
            }
            chat?.connectBlocking()
            playerChannels[uuid] = chat
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
                playerChannels[player.uniqueId]?.closeBlocking()
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
            client.fetchChannel(id).channelName
        } catch (e: Exception) {
            "알 수 없음"
        }
    }

    fun getChannelFollower(id: String): String {
        return try {
            client.fetchChannel(id).followerCount.toString()
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
    fun getPlayerChannel(uuid: UUID): ChzzkChat? = playerChannels[uuid]
    fun getChzzk(): ChzzkChat?= chat
}