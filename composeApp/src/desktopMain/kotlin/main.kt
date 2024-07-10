import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.navigation.compose.rememberNavController
import id.slavnt.composemp.di.initKoin
import id.slavnt.composemp.presentation.navigation.NavigationDesktop

fun main() = application {

   initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeMP",
    ) {
        MyComposeAppTheme {
            NavigationDesktop()
        }
    }
}