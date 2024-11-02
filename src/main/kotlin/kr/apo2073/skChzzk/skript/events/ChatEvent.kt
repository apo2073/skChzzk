package kr.apo2073.skChzzk.skript.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import kr.apo2073.skChzzk.utils.ChzzkChatEvent
import org.bukkit.event.Event

class ChatEvent: SkriptEvent() {
    companion object {
        init {
            Skript.registerEvent(
                "Chzzk Chat", // 문서에 표시될 이벤트 이름
                ChatEvent::class.java, // 이벤트 클래스
                ChzzkChatEvent::class.java, // 실제 이벤트
                "[chzzk] (chat|message)" // 구문 패턴
            )
        }
    }

    override fun init(args: Array<Literal<*>>, matchedPattern: Int, parseResult: SkriptParser.ParseResult): Boolean {
        return true
    }

    override fun check(event: Event): Boolean {
        return event is ChzzkChatEvent
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "chzzk chat message"
    }
}