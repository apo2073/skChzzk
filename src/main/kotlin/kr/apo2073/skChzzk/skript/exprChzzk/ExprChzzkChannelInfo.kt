package kr.apo2073.skChzzk.skript.exprChzzk

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.utils.ChzzkChatManager
import org.bukkit.event.Event

class ExprChzzkChannelInfo : SimpleExpression<String>() {
    companion object {
        init {
            Skript.registerExpression(
                ExprChzzkChannelInfo::class.java,
                String::class.java,
                ExpressionType.PROPERTY,
                "[(the|a)] [chzzk] channel name",
                "[(the|a)] [chzzk] channel followers"
            )
        }
    }

    private var pattern: Int = 0

    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        pattern = matchedPattern
        return true
    }

    override fun get(event: Event): Array<String?> {
        val channelInfo = ChzzkChatManager.getCurrentChannelInfo()
        return when (pattern) {
            0 -> arrayOf(channelInfo?.channelName ?: "알 수 없음")
            1 -> arrayOf(channelInfo?.followerCount?.toString() ?: "0")
            else -> arrayOf()
        }
    }

    override fun isSingle() = true
    override fun getReturnType() = String::class.java
    override fun toString(e: Event?, debug: Boolean) = "chzzk channel info"
}