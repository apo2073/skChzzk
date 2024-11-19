package kr.apo2073.skChzzk.skript

import ch.njol.skript.Skript
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.utils.ChzzkChatManager
import org.bukkit.entity.Player
import org.bukkit.event.Event

class EffectChzzkConnect : Effect() {
    companion object {
        init {
            Skript.registerEffect(
                EffectChzzkConnect::class.java,
                "connect %player% to [chzzk] channel %string%"
            )
        }
    }

    private var channelId: Expression<String>? = null
    private var player: Expression<Player>?= null

    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        channelId = exprs[1] as Expression<String>
        player=exprs[0] as Expression<Player>
        return true
    }

    override fun execute(event: Event) {
        val id = channelId?.getSingle(event) ?: return
        val player=player?.getSingle(event) ?: return
        ChzzkChatManager.connect(id, player.uniqueId)
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "connect to chzzk channel ${channelId?.toString(event, debug)}"
    }
}