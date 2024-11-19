package kr.apo2073.skChzzk.skript.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import kr.apo2073.skChzzk.utils.ChzzkChatEvent
import kr.apo2073.skChzzk.utils.ChzzkDonationEvent
import kr.apo2073.skChzzk.utils.ChzzkSubscriptionEvent

class ChzzkEvent: SimpleEvent() {
    companion object {
        init {
            Skript.registerEvent("[chzzk] chat message",
                SimpleEvent::class.java,
                ChzzkChatEvent::class.java,
                "[chzzk] (chat|message)"
            )
            Skript.registerEvent("[chzzk] donation",
                SimpleEvent::class.java,
                ChzzkDonationEvent::class.java,
                "[chzzk] donation"
            )
            Skript.registerEvent("[chzzk] subscription",
                SimpleEvent::class.java,
                ChzzkSubscriptionEvent::class.java,
                "[chzzk] subscription"
            )
        }
    }
}