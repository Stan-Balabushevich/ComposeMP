import androidx.compose.ui.window.ComposeUIViewController
import id.slavnt.composemp.di.initKoin
import id.slavnt.composemp.presentation.navigation.NavigationMobile

fun MainViewController() = ComposeUIViewController(
                      configure = {
                        initKoin()
                      }
) {
    MyComposeAppTheme {
        NavigationMobile()
    }

}