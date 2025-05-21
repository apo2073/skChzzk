package kr.apo2073.skChzzk.utils

import com.google.gson.Gson
import com.google.gson.JsonObject
import kr.apo2073.skChzzk.SkChzzk
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

object VersionManager {
    private val GITHUB_API="https://api.github.com/repos/apo2073/skChzzk/releases/latest"

    fun checkLatest():Boolean {
        val ver=SkChzzk.plugin.pluginMeta.version.split("-")[0].toDouble()
        val latest= getLatestVer().split("-")[0].toDouble()
        if (ver>=latest) return true
        SkChzzk.plugin.server.consoleSender.apply {
            sendMessage(Component.text("SkChzzk의 새로운 버전이 출시 되었습니다. ( 현재 버전: ${SkChzzk.plugin.pluginMeta.version}, 최신버전: ${getLatestVer()}", NamedTextColor.RED))
            sendMessage(Component.text("다운로드 > \nhttps://github.com/apo2073/skChzzk/releases", NamedTextColor.WHITE))
        }
        return false
    }

    fun getLatestVer():String {
        val json=Gson().fromJson(fetchData(GITHUB_API), JsonObject::class.java)
        return json.get("tag_name").asString
    }

    private fun fetchData(url: String): String {
        return URL(url).httpRequest {
            requestMethod = "GET"
            setRequestProperty("Accept", "application/vnd.github.v3+json")
//            setRequestProperty("Authorization", "Bearer ${File("${SkChzzk.instance.dataFolder}/TOKEN.txt").readText().trim()}")
            inputStream.bufferedReader().use { it.readText() }
        }
    }

    private fun <T> URL.httpRequest(requester: (HttpURLConnection.() -> T)): T {
        return with(openConnection() as HttpURLConnection) { requester.invoke(this) }
    }
}