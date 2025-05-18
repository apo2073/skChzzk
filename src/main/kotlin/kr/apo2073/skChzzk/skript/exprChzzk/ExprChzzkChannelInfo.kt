package kr.apo2073.skChzzk.skript.exprChzzk

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.chzzk.ChzzkChatManager
import org.bukkit.event.Event

class ExprChzzkChannelInfo : SimpleExpression<String>() {
    companion object {
        init {
            Skript.registerExpression(
                ExprChzzkChannelInfo::class.java,
                String::class.java,
                ExpressionType.PROPERTY,
                "[the] [chzzk] channel name of %string%",
                "[the] [chzzk] channel follower[s] of %string%"
            )
        }
    }

    private var pattern: Int = 0
    private lateinit var channelId: Expression<String>

    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        pattern = matchedPattern
        channelId = exprs[0] as Expression<String>
        return true
    }

    override fun get(event: Event): Array<String?> {
        try {
            val id = channelId.getSingle(event) ?: return arrayOf()
            return when (pattern) {
                0 -> arrayOf(ChzzkChatManager.getChannelName(id))
                1 -> arrayOf(ChzzkChatManager.getChannelFollower(id))
                else -> arrayOf()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return arrayOf()
        }
    }

    override fun isSingle() = true
    override fun getReturnType() = String::class.java
    override fun toString(e: Event?, debug: Boolean) = "chzzk channel info"
}