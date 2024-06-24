import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import database.getPeopleDatabase

fun main() = application {

    val dbDao = remember { getPeopleDatabase().peopleDao() }

    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeMP",
    ) {
        App(batteryManager = BatteryManager(), peopleDao = dbDao)
    }
}