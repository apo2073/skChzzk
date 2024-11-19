package kr.apo2073.skChzzk.skript.exprChzzk

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Event

class ExprPlayerFrom: SimpleExpression<Player>() {
    companion object {
        init {
            Skript.registerExpression(
                ExprPlayerFrom::class.java,
                Player::class.java,
                ExpressionType.PROPERTY,
                "%string% as player"
            )
        }
    }
    private lateinit var strExpr: Expression<String>

    override fun get(event: Event): Array<Player> {
        try {
            return arrayOf(
                Bukkit.getPlayer(strExpr.getSingle(event).toString())
                    ?: return arrayOf()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return arrayOf()
        }
    }


    override fun init(exprs: Array<Expression<*>>, matchedPattern: Int, isDelayed: Kleenean, parseResult: SkriptParser.ParseResult): Boolean {
        strExpr = exprs[0] as Expression<String>
        return true
    }
    override fun isSingle() = true
    override fun getReturnType() = Player::class.java

    override fun toString(e: Event?, debug: Boolean) = "get player from name/uuid"
}