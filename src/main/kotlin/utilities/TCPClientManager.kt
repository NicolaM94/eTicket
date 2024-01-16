package utilities

import kotlinx.serialization.json.Json
import serializables.Settings
import java.io.File
import java.net.Socket

class StampingTCPClient {

    private var socket :Socket = Socket()

    fun createSocketFromSettings () {
        val text = File("settings.json").readText()
        val settings = Json.decodeFromString<Settings>(text)

        socket = Socket(settings.Address, settings.Port.toInt())
    }

    fun write (msg :String) {
        socket.getOutputStream().write(msg.toByteArray())
    }

    fun listen () :String {
        val incomingMsg = socket.getInputStream()
        val msg = String(incomingMsg.readAllBytes())
        println(msg)
        socket.close()
        return when (msg) {
            "0\n" -> "Tibratura eseguita!"
            "1\n" -> "Errore: utente non trovato"
            else -> "Errore imprevisto: contattare il supporto"
        }
    }
}
