import androidx.compose.ui.window.ComposeUIViewController
import id.slavnt.composemp.di.initKoin
import presentation.App

fun MainViewController() = ComposeUIViewController(
                      configure = {
                        initKoin()
                      }
) {
    App()
}