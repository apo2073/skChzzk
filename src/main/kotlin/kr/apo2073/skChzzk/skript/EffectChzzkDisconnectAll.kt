package kr.apo2073.skChzzk.skript

import ch.njol.skript.Skript
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.chzzk.ChzzkChatManager
import org.bukkit.event.Event

class EffectChzzkDisconnectAll : Effect() {
    companion object {
        init {
            Skript.registerEffect(
                EffectChzzkDisconnectAll::class.java,
                "disconnect (all|every) channel"
            )
        }
    }

    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        return true
    }

    override fun execute(event: Event) {
        ChzzkChatManager.disconnectAll()
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "disconnect from chzzk"
    }
}