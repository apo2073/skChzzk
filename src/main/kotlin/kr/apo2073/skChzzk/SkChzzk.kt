package kr.apo2073.skChzzk

import ch.njol.skript.Skript
import ch.njol.skript.SkriptAddon
import kr.apo2073.skChzzk.skript.EffectChzzkConnect
import kr.apo2073.skChzzk.skript.EffectChzzkDisconnect
import kr.apo2073.skChzzk.skript.events.ChatEvent
import kr.apo2073.skChzzk.skript.events.DonationEvent
import kr.apo2073.skChzzk.skript.events.SubscriptionEvent
import kr.apo2073.skChzzk.skript.exprChzzk.ExprChzzkChannelInfo
import kr.apo2073.skChzzk.skript.exprChzzk.ExprChzzkChat
import kr.apo2073.skChzzk.skript.exprChzzk.ExprChzzkDonation
import kr.apo2073.skChzzk.skript.exprChzzk.ExprChzzkSubscription
import kr.apo2073.skChzzk.utils.ChzzkChatManager
import kr.apo2073.skChzzk.utils.ChzzkEvent
import org.bukkit.plugin.java.JavaPlugin

class SkChzzk : JavaPlugin() {
    companion object { lateinit var instance:SkChzzk }
    private lateinit var addon:SkriptAddon
    override fun onEnable() {
        instance=this
        if (server.pluginManager.getPlugin("Skript")!=null) {
            addon=Skript.registerAddon(this)
            ExprChzzkChat()
            ExprChzzkDonation()
            ExprChzzkSubscription()
            ExprChzzkChannelInfo()

            ChzzkEvent()
            ChatEvent()
            DonationEvent()
            SubscriptionEvent()

            EffectChzzkConnect()
            EffectChzzkDisconnect()
        }
    }

    override fun onDisable() {
        ChzzkChatManager.disconnect()
    }
}
