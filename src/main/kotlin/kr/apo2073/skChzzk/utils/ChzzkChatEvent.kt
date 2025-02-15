package kr.apo2073.skChzzk.utils

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import xyz.r2turntrue.chzzk4j.chat.ChatMessage
import xyz.r2turntrue.chzzk4j.chat.ChzzkChat
import xyz.r2turntrue.chzzk4j.chat.DonationMessage
import xyz.r2turntrue.chzzk4j.chat.SubscriptionMessage

data class ChzzkChatEvent(val message: ChatMessage, val chat:ChzzkChat): Event() {
    override fun getEventName() = "ChzzkChatEvent"
    override fun getHandlers(): HandlerList = getHandlerList()
    companion object {
        private val handlers = HandlerList()
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }
}

data class ChzzkDonationEvent(val message: DonationMessage, val chat:ChzzkChat) : Event() {
    override fun getEventName() = "ChzzkDonationEvent"
    override fun getHandlers(): HandlerList = getHandlerList()
    companion object {
        private val handlers = HandlerList()
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }
}

data class ChzzkSubscriptionEvent(val message: SubscriptionMessage, val chat:ChzzkChat) : Event() {
    override fun getEventName() = "ChzzkSubscriptionEvent"
    override fun getHandlers(): HandlerList = getHandlerList()
    companion object {
        private val handlers = HandlerList()
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }
}