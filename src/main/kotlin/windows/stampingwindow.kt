package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun StampingWindow (outcome :String) {

    var background by remember { mutableStateOf(Color(182, 255, 208)) }
    var color by remember { mutableStateOf(Color(21, 22, 21)) }

    if (outcome != "Tibratura eseguita!") {
        background = Color(255, 137, 137)
        color = Color(21, 22, 21)
    }

    MaterialTheme {
        Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().background(Color(238, 248, 252))) {

            Text (
                text = outcome,
                fontWeight = FontWeight.Bold,
                fontSize = 32F.sp,
                color = color,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(50f.dp)
            )
        }
    }
}