package kr.apo2073.skChzzk.skript.exprChzzk

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.chzzk.ChzzkChatManager
import kr.apo2073.skChzzk.chzzk.ChzzkSubscriptionEvent
import org.bukkit.event.Event

class ExprChzzkSubscription : SimpleExpression<String>() {
    companion object {
        init {
            Skript.registerExpression(
                ExprChzzkSubscription::class.java,
                String::class.java,
                ExpressionType.PROPERTY,
                "[the] [chzzk] subscription (sender|name)",
                "[the] [chzzk] subscription month[s]",
                "[the] [chzzk] subscription tier",
                "[the] [chzzk] channel id",
                "[the] [chzzk] channel name",
                "[the] [chzzk] channel player"
            )
        }
    }

    private var pattern: Int = 0

    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        pattern = matchedPattern
        return true
    }

    override fun get(event: Event): Array<String?> {
        if (event is ChzzkSubscriptionEvent) {
            val msg = event.message
            val chat=event.chat
            return when (pattern) {
                0 -> arrayOf(msg.profile?.nickname ?: "익명")
                1 -> arrayOf(msg.subscriptionMonth.toString())
                2 -> arrayOf(msg.subscriptionTierName)
                3 -> arrayOf(chat.channelId)
                4 -> arrayOf(ChzzkChatManager.getChannelName(chat.channelId))
                5 -> arrayOf(ChzzkChatManager.getChannelPlayer(chat.channelId))
                else -> arrayOf()
            }
        }
        return arrayOf()
    }

    override fun isSingle() = true
    override fun getReturnType() = String::class.java
    override fun toString(e: Event?, debug: Boolean) = "chzzk subscription info"
}