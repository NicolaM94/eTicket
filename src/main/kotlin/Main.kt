import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import utilities.CheckSettingsFile
import windows.MainWindow


fun main() = application {
        Window(onCloseRequest = ::exitApplication, title = "eTicket", resizable = false, icon = painterResource("stamp-icon.svg")) {
            CheckSettingsFile()
            MainWindow()
        }
}



