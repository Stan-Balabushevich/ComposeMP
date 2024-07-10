import androidx.compose.ui.window.ComposeUIViewController
import id.slavnt.composemp.di.initKoin
import id.slavnt.composemp.presentation.mainscreen.MovieScreenMobile

fun MainViewController() = ComposeUIViewController(
                      configure = {
                        initKoin()
                      }
) {
    MyComposeAppTheme {
        MovieScreenMobile()
    }

}