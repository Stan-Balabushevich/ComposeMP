import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import id.slavnt.composemp.di.platformModule
import id.slavnt.composemp.di.presentationModule
import org.koin.core.context.startKoin
import presentation.App

fun main() = application {

    startKoin {
        modules(platformModule, presentationModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeMP",
    ) {
        App()
    }
}