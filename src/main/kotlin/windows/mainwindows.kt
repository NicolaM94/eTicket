package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import utilities.StampingTCPClient

fun coverPassword (text: AnnotatedString) : TransformedText {
    val a = AnnotatedString("*".repeat(text.text.length))
    return TransformedText(a, OffsetMapping.Identity)
}

@Composable
@Preview
fun MainWindow  () {

    var frontWindow by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var outcome by remember { mutableStateOf("") }

    MaterialTheme {
        Column (
            Modifier.fillMaxSize().background(Color(238, 248, 252)), verticalArrangement = Arrangement.spacedBy(10f.dp), horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text="eTicket",
                fontWeight = FontWeight.W700,
                fontSize = TextUnit(4.5F, TextUnitType.Em)
            )
            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "Il cartellino rapido",
                fontWeight = FontWeight.W500,
                fontSize = TextUnit(1F, TextUnitType.Em)
            )
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(2, 62, 138),
                    unfocusedBorderColor = Color(3, 4, 94),
                    unfocusedLabelColor = Color(3, 4, 94),
                    focusedLabelColor = Color(0, 119, 182)
                ),
                modifier = Modifier,
                value = username,
                onValueChange = {username = it; },
                label = { Text("Username") },
                textStyle = TextStyle(fontWeight = FontWeight.W600),
                maxLines = 1
            )
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(2, 62, 138),
                    unfocusedBorderColor = Color(3, 4, 94),
                    unfocusedLabelColor = Color(3, 4, 94),
                    focusedLabelColor = Color(0, 119, 182)
                ),
                visualTransformation = { coverPassword(it) },
                value = password,
                onValueChange = {password = it; println(password) },
                label = { Text("Password") },
                textStyle = TextStyle(fontWeight = FontWeight.W600),
                maxLines = 1
            )
            Button(
                shape = CircleShape,
                modifier = Modifier.size(100.dp).padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(3, 4, 94),
                    contentColor = Color.White
                ),
                onClick = { frontWindow = "stampwindow" },
            ) {
                Text("Timbra", fontSize = TextUnit(16f, TextUnitType.Sp))
            }
            Button(
                modifier = Modifier.align(Alignment.End).padding(top = 60.dp, end = 50.dp),
                onClick = { utilities.CheckSettingsFile(); frontWindow = "settingswindow" },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(3, 4, 94),
                    contentColor = Color.White
                )
            ) {
                Image(painterResource("settingsico.svg"), contentDescription = null, modifier = Modifier.size(25.dp))
            }
        }
    }

    if (frontWindow == "settingswindow") {

        Window(
            onCloseRequest = {frontWindow = "mainwindow"},
            icon = painterResource("stamp-icon.svg"),
            title = "Impostazioni",
            resizable = false,
            state = WindowState(width = 300.dp, height = 300.dp)
        ) {
            SettingsDialog()
        }
    }

    if (frontWindow == "stampwindow") {

        println("Call tcp client...")
        val stc = StampingTCPClient()
        stc.createSocketFromSettings()
        println("Writing to client...")
        stc.write("$username:$password:\n")
        outcome = stc.listen()
        println("Done!")

        DialogWindow(
            onCloseRequest = {frontWindow = "mainwindow"},
            icon = painterResource("stamp-icon.svg"),
            title = "Timbratura",
            resizable = false,
        ) {
            StampingWindow(outcome)
        }
    }
}