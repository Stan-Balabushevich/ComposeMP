import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import id.slavnt.composemp.di.initKoin
import id.slavnt.composemp.presentation.mainscreen.MovieScreenDesktop

fun main() = application {

   initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeMP",
    ) {
//        App()

//        MovieScreen()
        MovieScreenDesktop()
    }
}