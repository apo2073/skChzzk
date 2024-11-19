package kr.apo2073.skChzzk.skript.exprChzzk

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.utils.ChzzkChatEvent
import kr.apo2073.skChzzk.utils.ChzzkChatManager
import org.bukkit.event.Event

class ExprChzzkChat : SimpleExpression<String>() {
    companion object {
        init {
            Skript.registerExpression(
                ExprChzzkChat::class.java,
                String::class.java,
                ExpressionType.PROPERTY,
                "[the] [chzzk] (message|chat) content",
                "[the] [chzzk] (message|chat) sender",
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
        if (event is ChzzkChatEvent) {
            val msg = event.message
            val chat=event.chat
            return when (pattern) {
                0 -> arrayOf(msg.content)
                1 -> arrayOf(msg.profile?.nickname ?: "익명")
                2 -> arrayOf(chat.channelId)
                3 -> arrayOf(ChzzkChatManager.getChannelName(chat.channelId))
                4 -> arrayOf(ChzzkChatManager.getChannelPlayer(chat.channelId))
                else -> arrayOf()
            }
        }
        return arrayOf()
    }

    override fun isSingle() = true
    override fun getReturnType() = String::class.java
    override fun toString(e: Event?, debug: Boolean) = "chzzk chat info"
}