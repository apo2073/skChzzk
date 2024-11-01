package kr.apo2073.skChzzk

import kr.apo2073.skChzzk.skript.EffectChzzkConnect
import kr.apo2073.skChzzk.skript.ExprChzzkChat
import kr.apo2073.skChzzk.utils.ChzzkEvent
import org.bukkit.plugin.java.JavaPlugin

class SkChzzk : JavaPlugin() {
    companion object {lateinit var instance:SkChzzk}
    override fun onEnable() {
        instance=this
        if (server.pluginManager.getPlugin("Skript")!=null) {
            ExprChzzkChat()
            ChzzkEvent()
            EffectChzzkConnect()
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
