package npsm.study.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.IllegalTimeZoneException
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import what_is_me.composeapp.generated.resources.Res
import what_is_me.composeapp.generated.resources.compose_multiplatform

data class Country(val name: String, val zone: TimeZone)

fun currentTimeAt(location: String, zone: TimeZone): String {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time

    return "The time in $location is ${localTime.formatted()}"
}

fun countries() = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo")),
    Country("France", TimeZone.of("Europe/Paris")),
    Country("Mexico", TimeZone.of("America/Mexico_City")),
    Country("Indonesia", TimeZone.of("Asia/Jakarta")),
    Country("Egypt", TimeZone.of("Africa/Cairo")),
)

@Composable
@Preview
fun App() {
    // MaterialTheme: Application의 모양을 설정.(색상, 모양, 타이포그라피 등)
    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }
        var timeAtLocation by remember { mutableStateOf("No location selected") }

        Column {
            Column(modifier = Modifier.padding(20.dp)) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        timeAtLocation,
                        style = TextStyle(fontSize = 20.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                    )
                    Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                        DropdownMenu(
                            expanded = showCountries,
                            onDismissRequest = { showCountries = false }
                        ) {
                            countries().forEach { (name, zone) ->
                                DropdownMenuItem(
                                    onClick = {
                                        timeAtLocation = currentTimeAt(name, zone)
                                        showCountries = false
                                    }
                                ) {
                                    Text(name)
                                }
                            }
                        }
                    }

                    Button(modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                        onClick = { showCountries = !showCountries }) {
                        Text("Select Location")
                    }
                }
            }
        }
    }

}

