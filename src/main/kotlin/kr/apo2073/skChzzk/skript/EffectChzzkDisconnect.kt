package kr.apo2073.skChzzk.skript

import ch.njol.skript.Skript
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.utils.ChzzkChatManager
import org.bukkit.entity.Player
import org.bukkit.event.Event

class EffectChzzkDisconnect : Effect() {
    companion object {
        init {
            Skript.registerEffect(
                EffectChzzkDisconnect::class.java,
                "disconnect %player%'s channel [from chzzk]"
            )
        }
    }

    private var player: Expression<Player>?= null

    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        player=exprs[0] as Expression<Player>
        return true
    }

    override fun execute(event: Event) {
        val p=this.player?.getSingle(event) ?: return
        ChzzkChatManager.disconnect(p.uniqueId)
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "disconnect from chzzk"
    }
}