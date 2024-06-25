import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import database.getPeopleDatabase
import id.slavnt.composemp.di.platformModule
import org.koin.core.context.startKoin

fun main() = application {

    val dbDao = remember { getPeopleDatabase().peopleDao() }

    startKoin {
        modules(platformModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeMP",
    ) {
        App(batteryManager = BatteryManager(), peopleDao = dbDao)
    }
}