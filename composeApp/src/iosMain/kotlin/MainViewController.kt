import androidx.compose.ui.window.ComposeUIViewController
import id.slavnt.composemp.di.initKoin
import id.slavnt.composemp.presentation.App

fun MainViewController() = ComposeUIViewController(
                      configure = {
                        initKoin()
                      }
) {
    App()
}