import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getPeopleDatabase

fun MainViewController() = ComposeUIViewController {

    val dbDao = remember { getPeopleDatabase().peopleDao() }

    App(batteryManager = BatteryManager(), peopleDao = dbDao) }