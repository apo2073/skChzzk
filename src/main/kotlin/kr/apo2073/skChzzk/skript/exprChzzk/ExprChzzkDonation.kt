package kr.apo2073.skChzzk.skript.exprChzzk

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.utils.ChzzkDonationEvent
import org.bukkit.event.Event

class ExprChzzkDonation : SimpleExpression<String>() {
    companion object {
        init {
            Skript.registerExpression(
                ExprChzzkDonation::class.java,
                String::class.java,
                ExpressionType.PROPERTY,
                "[the] chzzk donation (amount|money)",
                "[the] chzzk donation (sender|name)",
                "[the] chzzk donation (content|message)"
            )
        }
    }

    private var pattern: Int = 0

    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        pattern = matchedPattern
        return true
    }

    override fun get(event: Event): Array<String?> {
        if (event is ChzzkDonationEvent) {
            val msg = event.message
            return when (pattern) {
                0 -> arrayOf(msg.payAmount.toString())
                1 -> arrayOf(msg.profile?.nickname ?: "익명")
                2 -> arrayOf(msg.content)
                else -> arrayOf()
            }
        }
        return arrayOf()
    }

    override fun isSingle() = true
    override fun getReturnType() = String::class.java
    override fun toString(e: Event?, debug: Boolean) = "chzzk donation info"
}