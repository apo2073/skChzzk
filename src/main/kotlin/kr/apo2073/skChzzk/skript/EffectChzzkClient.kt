package kr.apo2073.skChzzk.skript

import ch.njol.skript.Skript
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.SkChzzk
import org.bukkit.event.Event

class EffectChzzkClient: Effect() {
    companion object {
        init {
            Skript.registerEffect(
                EffectChzzkClient::class.java,
                "[chzzk] client set with id %string% and secret %string%"
            )
        }
    }

    private val data= SkChzzk.chzzkData
    private var id: Expression<String>? = null
    private var secret: Expression<String>? = null

    override fun init(
        exprs: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        id=exprs[0] as Expression<String>
        secret=exprs[1] as Expression<String>
        return true
    }

    override fun execute(event: Event) {
        val id = id?.getSingle(event) ?: return
        val secret=secret?.getSingle(event) ?: return
        data.setClientKey(id, secret)
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }
}