package kr.apo2073.skChzzk.skript

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.utils.ChzzkChatEvent
import org.bukkit.event.Event

class ExprChzzkChat: SimpleExpression<Any>() {
    companion object {
        init {
            Skript.registerExpression(
                ExprChzzkChat::class.java,
                Any::class.java,
                ExpressionType.SIMPLE,
                "chzzk message content",
                "chzzk message sender",
                "chzzk channel id"
            )
        }
    }

    private var pattern: Int = 0

    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        pattern = matchedPattern
        return true
    }

    override fun get(event: Event): Array<Any?> {
        if (event is ChzzkChatEvent) {
            val msg = event.message
            return when (pattern) {
                0 -> arrayOf(msg.content)
                1 -> arrayOf(msg.profile?.nickname ?: "익명")
                else -> arrayOf()
            }
        }
        return arrayOf()
    }

    override fun isSingle() = true

    override fun getReturnType() = Any::class.java

    override fun toString(e: Event?, debug: Boolean) = "chzzk chat info"
}