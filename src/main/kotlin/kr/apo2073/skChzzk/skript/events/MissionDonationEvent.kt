package kr.apo2073.skChzzk.skript.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import kr.apo2073.skChzzk.chzzk.ChzzkMissionDonationEvent
import org.bukkit.event.Event

class MissionDonationEvent: SkriptEvent() {
    companion object {
        init {
            Skript.registerEvent(
                "Chzzk Mission Donation",
                MissionDonationEvent::class.java,
                ChzzkMissionDonationEvent::class.java,
                "[chzzk] mission donation"
            )
        }
    }

    override fun init(args: Array<Literal<*>>, matchedPattern: Int, parseResult: SkriptParser.ParseResult): Boolean {
        return true
    }

    override fun check(event: Event): Boolean {
        return event is ChzzkMissionDonationEvent
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "chzzk mission donation"
    }
}