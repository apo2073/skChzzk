package kr.apo2073.skChzzk.skript.exprChzzk

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.chzzk.ChzzkChatManager
import kr.apo2073.skChzzk.chzzk.ChzzkMissionDonationEvent
import org.bukkit.event.Event

class ExprChzzkMissionDonation: SimpleExpression<String>() {
    companion object {
        init {
            Skript.registerExpression(
                ExprChzzkMissionDonation::class.java,
                String::class.java,
                ExpressionType.PROPERTY,
                "[(the|a)] [chzzk] donation (amount|money)",
                "[(the|a)] [chzzk] donation (sender|name)",
                "[(the|a)] [chzzk] donation (content|message)",
                "[the] [chzzk] channel id",
                "[the] [chzzk] channel name",
                "[the] [chzzk] channel connector",

                "[the] mission content",
                "[the] mission is (success|done)",
                "[the] mission status"
            )
        }
    }

    private var pattern: Int = 0

    override fun init(
        exprs: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        pattern = matchedPattern
        return true
    }

    override fun get(event: Event): Array<String> {
        if (event is ChzzkMissionDonationEvent) {
            val msg = event.message
            val chat = event.chat

            return when (pattern) {
                0 -> arrayOf("${msg.payAmount}")
                1 -> arrayOf(msg.profile?.nickname ?: "익명")
                2 -> arrayOf(msg.content ?: "")
                3 -> arrayOf(chat.channelId ?: "알 수 없음")
                4 -> arrayOf(ChzzkChatManager.getChannelName(chat.channelId ?: "알 수 없음"))
                5 -> arrayOf(ChzzkChatManager.getChannelPlayer(chat.channelId))
                6-> arrayOf(msg.missionText)
                7-> arrayOf(msg.isMissionSucceed.toString())
                8-> arrayOf(msg.missionStatus)
                else -> arrayOf()
            }
        }
        return arrayOf()
    }

    override fun isSingle() = true
    override fun getReturnType() = String::class.java
    override fun toString(e: Event?, debug: Boolean) = "chzzk mission donation info"
}