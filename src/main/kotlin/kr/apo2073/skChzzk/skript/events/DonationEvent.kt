package kr.apo2073.skChzzk.skript.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import kr.apo2073.skChzzk.chzzk.ChzzkDonationEvent
import org.bukkit.event.Event

class DonationEvent: SkriptEvent() {
    companion object {
        init {
            Skript.registerEvent(
                "Chzzk Donation",
                DonationEvent::class.java,
                ChzzkDonationEvent::class.java,
                "[chzzk] (donation|done)"
            )
        }
    }

    override fun init(args: Array<Literal<*>>, matchedPattern: Int, parseResult: SkriptParser.ParseResult): Boolean {
        return true
    }

    override fun check(event: Event): Boolean {
        return event is ChzzkDonationEvent
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "chzzk donation"
    }
}