package kr.apo2073.skChzzk

import ch.njol.skript.Skript
import ch.njol.skript.SkriptAddon
import kr.apo2073.skChzzk.skript.EffectChzzkConnect
import kr.apo2073.skChzzk.skript.EffectChzzkDisconnect
import kr.apo2073.skChzzk.skript.EffectChzzkDisconnectAll
import kr.apo2073.skChzzk.skript.events.ChatEvent
import kr.apo2073.skChzzk.skript.events.ChzzkEvent
import kr.apo2073.skChzzk.skript.events.DonationEvent
import kr.apo2073.skChzzk.skript.events.SubscriptionEvent
import kr.apo2073.skChzzk.skript.exprChzzk.*
import kr.apo2073.skChzzk.utils.ChzzkChatManager
import kr.apo2073.skChzzk.utils.VersionManager.getLatestVer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.plugin.java.JavaPlugin

class SkChzzk : JavaPlugin() {
    companion object { lateinit var instance:SkChzzk }
    private lateinit var addon:SkriptAddon
    private lateinit var chatManager: ChzzkChatManager
    override fun onEnable() {
        instance=this
        chatManager=ChzzkChatManager

        if (this.pluginMeta.version==getLatestVer()) {
            this.server.consoleSender.apply {
                sendMessage(
                    Component.text("SkChzzk의 새로운 버전이 출시 되었습니다", NamedTextColor.RED)
                )
                sendMessage(Component.text("다운로드 > \nhttps://github.com/apo2073/skChzzk/releases", NamedTextColor.WHITE))
            }
        }

        if (server.pluginManager.getPlugin("Skript")!=null) {
            addon=Skript.registerAddon(this)
            
            ExprChzzkChat()
            ExprChzzkDonation()
            ExprChzzkSubscription()
            ExprChzzkChannelInfo()
            ExprChzzkPlayer()
            ExprPlayerFrom()
            
            ChzzkEvent()
            ChatEvent()
            DonationEvent()
            SubscriptionEvent()

            EffectChzzkConnect()
            EffectChzzkDisconnect()
            EffectChzzkDisconnectAll()
        }
    }

    override fun onDisable() {
        chatManager.disconnectAll()
    }
}
