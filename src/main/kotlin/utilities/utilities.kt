package utilities

import com.google.gson.Gson
import serializables.Settings
import java.io.File

fun CheckSettingsFile () {
   if (!File("settings.json").exists()) {
       File("settings.json").createNewFile()
       with(File("settings.json")) {
           this.writeText("""{"Address":"","Port":""}""")
       }
   }
}

fun SaveSettings (s : Settings) {
    val gson = Gson()
    gson.newBuilder().setPrettyPrinting()
    val serialized = gson.toJson(s)
    try {
        with(File("settings.json")) {
            this.writeText(serialized)
        }
    } catch (err :Exception) {
        println(err.message)
    }
}

fun LoadSettings () : Settings {
    val gson = Gson()
    return  gson.fromJson(File("settings.json").reader(), Settings::class.java)
}