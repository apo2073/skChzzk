package kr.apo2073.skChzzk.skript

import ch.njol.skript.Skript
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import kr.apo2073.skChzzk.SkChzzk
import org.bukkit.event.Event
import xyz.r2turntrue.chzzk4j.auth.ChzzkLegacyLoginAdapter
import xyz.r2turntrue.chzzk4j.auth.ChzzkSimpleUserLoginAdapter
import xyz.r2turntrue.chzzk4j.naver.NaverAutologinAdapter

class EffectChzzkAuth: Effect() {
    companion object {
        init {
            Skript.registerEffect(
                EffectChzzkAuth::class.java,
                "[chzzk] auth as (api|naver|cookies) with %string% and %string%"
            )
        }
    }
    private lateinit var authType: String
    private lateinit var expr1: Expression<String>
    private lateinit var expr2: Expression<String>
    override fun init(
        exprs: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        authType = when (parseResult.mark) {
            0 -> "api"
            1 -> "naver"
            2-> "cookies"
            else -> return false
        }
        expr1 = exprs[0] as Expression<String>
        expr2 = exprs[1] as Expression<String>
        return true
    }

    private val chzzkData= SkChzzk.chzzkData
    override fun execute(event: Event) {
        val param1 = expr1.getSingle(event) ?: return
        val param2 = expr2.getSingle(event) ?: return
        when(authType) {
            "api"-> {
                chzzkData.addAdapter(ChzzkSimpleUserLoginAdapter(param1, param2))
            }
            "naver"-> {
                chzzkData.addAdapter(NaverAutologinAdapter(param1, param2))
            }
            "cookies"-> {
                chzzkData.addAdapter(ChzzkLegacyLoginAdapter(param1, param2))
            }
        }
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }
}