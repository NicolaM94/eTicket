package utilities

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import serializables.Settings
import java.io.File


fun CheckSettingsFile () {
   if (!File("settings.json").exists()) {
       File("settings.json").createNewFile()
       with(File("settings.json")) {
           val settings = Settings("192.168.1.254","3344")
           val json = Json.encodeToString(settings)
           this.writeText(json)
       }
   }
}

fun SaveSettings (s : Settings) {
    val json = Json.encodeToString(s)
    try {
        with(File("settings.json")) {
            this.writeText(json)
        }
    } catch (err :Exception) {
        println(err.message)
    }
}

fun LoadSettings () : Settings {
    val reader = File("settings.json").readText()
    return  Json.decodeFromString<Settings>(reader)
}