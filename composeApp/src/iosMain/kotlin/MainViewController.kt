import androidx.compose.ui.window.ComposeUIViewController
import presentation.App

fun MainViewController() = ComposeUIViewController {

  val koinInit = KoinInitializer

  if (!koinInit.isKoinStarted())
    koinInit.startKoinIfNeeded()


    App()
}