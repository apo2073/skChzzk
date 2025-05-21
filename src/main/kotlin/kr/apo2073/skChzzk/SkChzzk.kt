package kr.apo2073.skChzzk

import ch.njol.skript.Skript
import ch.njol.skript.SkriptAddon
import kr.apo2073.skChzzk.chzzk.ChzzkChatManager
import kr.apo2073.skChzzk.chzzk.ChzzkData
import kr.apo2073.skChzzk.skript.*
import kr.apo2073.skChzzk.skript.events.*
import kr.apo2073.skChzzk.skript.exprChzzk.*
import kr.apo2073.skChzzk.utils.VersionManager
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
            ExprChzzkMissionDonation()
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
