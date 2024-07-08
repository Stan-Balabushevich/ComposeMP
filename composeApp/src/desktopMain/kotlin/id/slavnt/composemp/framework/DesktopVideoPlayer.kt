package id.slavnt.composemp.framework

import androidx.compose.foundation.layout.fillMaxSize
//import javafx.application.Platform
//import javafx.embed.swing.JFXPanel
//import javafx.scene.Scene
//import javafx.scene.web.WebEngine
//import javafx.scene.web.WebView
import javax.swing.JPanel
import javax.swing.SwingUtilities
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import id.slavnt.composemp.domain.video.VideoPlayer

//class DesktopVideoPlayer<VideoPlayer> : id.slavnt.composemp.domain.video.VideoPlayer {
//    @Composable
//    override fun playVideo(videoKey: String) {
//        val youtubeUrl = "https://www.youtube.com/embed/$videoKey"
//        SwingPanel(
//            factory = {
//                val panel = JPanel()
//                val jfxPanel = JFXPanel()
//                panel.add(jfxPanel)
//                Platform.runLater {
//                    val webView = WebView()
//                    jfxPanel.scene = Scene(webView)
//                    val webEngine: WebEngine = webView.engine
//                    webEngine.load(youtubeUrl)
//                }
//                panel
//            },
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//}
