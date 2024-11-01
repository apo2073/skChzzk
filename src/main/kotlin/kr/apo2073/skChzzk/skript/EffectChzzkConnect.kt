package kr.apo2073.skChzzk.skript

import ch.njol.skript.Skript
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.utils.ChzzkChatManager
import org.bukkit.event.Event

class EffectChzzkConnect : Effect() {
    companion object {
        init {
            Skript.registerEffect(
                EffectChzzkConnect::class.java,
                "connect to chzzk channel %string%",
                "connect chzzk [channel] %string%"
            )
        }
    }

    private var channelId: Expression<String>? = null

    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        channelId = exprs[0] as Expression<String>
        return true
    }

    override fun execute(event: Event) {
        val id = channelId?.getSingle(event) ?: return
        ChzzkChatManager.instance.connect(id)
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "connect to chzzk channel ${channelId?.toString(event, debug)}"
    }
}