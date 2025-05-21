package kr.apo2073.skChzzk

import ch.njol.skript.Skript
import ch.njol.skript.SkriptAddon
import kr.apo2073.skChzzk.skript.EffectChzzkConnect
import kr.apo2073.skChzzk.skript.EffectChzzkDisconnect
import kr.apo2073.skChzzk.skript.EffectChzzkDisconnectAll
import kr.apo2073.skChzzk.skript.events.ChatEvent
import kr.apo2073.skChzzk.skript.events.ChzzkEvent
import kr.apo2073.skChzzk.skript.events.DonationEvent
import kr.apo2073.skChzzk.skript.events.MissionDonationEvent
import kr.apo2073.skChzzk.skript.events.SubscriptionEvent
import kr.apo2073.skChzzk.skript.exprChzzk.*
import kr.apo2073.skChzzk.chzzk.ChzzkChatManager
import kr.apo2073.skChzzk.chzzk.ChzzkData
import kr.apo2073.skChzzk.skript.EffectChzzkAuth
import kr.apo2073.skChzzk.skript.EffectChzzkClient
import kr.apo2073.skChzzk.utils.VersionManager
import kr.apo2073.skChzzk.utils.VersionManager.getLatestVer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.plugin.java.JavaPlugin

class SkChzzk : JavaPlugin() {
    companion object {
        lateinit var plugin:SkChzzk
            private set
        lateinit var chzzkData: ChzzkData
    }
    private lateinit var addon:SkriptAddon
    private lateinit var chatManager: ChzzkChatManager
    override fun onEnable() {
        plugin=this
        chzzkData= ChzzkData()
//        saveResource("TOKEN.txt", true)
        chatManager=ChzzkChatManager

        VersionManager.checkLatest()

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
            MissionDonationEvent()
            SubscriptionEvent()

            EffectChzzkConnect()
            EffectChzzkDisconnect()
            EffectChzzkDisconnectAll()
            EffectChzzkAuth()
            EffectChzzkClient()
        }
    }

    override fun onDisable() {
        chatManager.disconnectAll()
    }
}
