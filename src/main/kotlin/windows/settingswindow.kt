package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import serializables.Settings


@Composable
@Preview
fun SettingsDialog () {

    var settings :Settings = utilities.LoadSettings()
    var ip by remember { mutableStateOf(settings.Address) }
    var port by remember { mutableStateOf(settings.Port) }

    var buttonColor by remember { mutableStateOf(Color(3, 4, 94)) }


    MaterialTheme {
        Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().background(Color(238, 248, 252))) {

            Text("Impostazioni", fontWeight = FontWeight.Bold, fontSize = 24F.sp, modifier = Modifier.padding(vertical = Dp(15F)))

            TextField(
                value = ip,
                onValueChange = {ip = it},
                placeholder = { Text(text = "IP address", fontSize = 12F.sp) },
                modifier = Modifier.width(200F.dp).height(60F.dp).padding(vertical = 7f.dp),
                textStyle = TextStyle(fontSize = 12f.sp, fontWeight = FontWeight.SemiBold),
                maxLines = 1
            )
            TextField(
                value = port,
                onValueChange = {port = it},
                placeholder = {Text("Port address", fontSize = 12F.sp)},
                modifier = Modifier.width(200F.dp).height(60F.dp).padding(vertical = 7F.dp),
                textStyle = TextStyle(fontSize = 12f.sp, fontWeight = FontWeight.SemiBold),
                maxLines = 1
            )

            Button(
                onClick = {
                    utilities.SaveSettings(Settings(ip,port)); buttonColor = Color(73, 147, 103) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor,
                    contentColor = Color.White
                )
            ) {
                Text("Salva")
            }
        }
    }
}