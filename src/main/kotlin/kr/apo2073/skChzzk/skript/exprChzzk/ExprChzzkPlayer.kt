package kr.apo2073.skChzzk.skript.exprChzzk

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.chzzk.ChzzkChatManager
import org.bukkit.entity.Player
import org.bukkit.event.Event

class ExprChzzkPlayer: SimpleExpression<String>() {
    companion object {
        init {
            Skript.registerExpression(
                ExprChzzkPlayer::class.java,
                String::class.java,
                ExpressionType.SIMPLE,
                "[(a|the)] %player%'s [chzzk] (channelId|channel)",
                "[(a|the)] %player%'s [chzzk] (channelName|name)",
                "[(a|the)] %player%'s [chzzk] (follower|fol)"
            )
        }
    }
    private var pattern: Int = 0
    private var playerExpr: Expression<Player>?= null
    
    override fun get(event: Event): Array<String> {
        try {
            val player = playerExpr?.getSingle(event) ?: return arrayOf()
            val id=ChzzkChatManager.getPlayerChannel(player.uniqueId)?.channelId ?: "none"
            return when(pattern) {
                0-> arrayOf(id)
                1-> arrayOf(ChzzkChatManager.getChannelName(id))
                2-> arrayOf(ChzzkChatManager.getChannelFollower(id))
                else-> arrayOf()
            }
        } catch (e:Exception) {
            e.printStackTrace()
            return arrayOf()
        }
    }
    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        playerExpr=exprs[0] as Expression<Player>
        pattern=matchedPattern
        return true
    }
    
    override fun toString(e: Event?, debug: Boolean) = "player's channel info"
    override fun isSingle() = true
    override fun getReturnType() = String::class.java
}