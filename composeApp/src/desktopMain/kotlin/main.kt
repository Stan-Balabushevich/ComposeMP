import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import presentation.App

fun main() = application {

    val init = KoinInitializer()

    if (!init.isKoinStarted())
    init.startKoinIfNeeded()

    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeMP",
    ) {
        App()
    }
}