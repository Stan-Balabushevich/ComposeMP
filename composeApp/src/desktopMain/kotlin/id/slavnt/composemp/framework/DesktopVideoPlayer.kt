package id.slavnt.composemp.framework

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import id.slavnt.composemp.framework.video.VideoPlayer
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent
import java.awt.BorderLayout
import javax.swing.JPanel
import javax.swing.SwingUtilities

class DesktopVideoPlayer : VideoPlayer {



    // As an alternative, we can use the system browser to open YouTube URLs.
//    @Composable
//    override fun playVideo(videoKey: String) {
//        val youtubeUrl = "https://www.youtube.com/watch?v=$videoKey"
//        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
//            Desktop.getDesktop().browse(URI(youtubeUrl))
//        } else {
//            println("Desktop or browse action not supported")
//        }
//    }



    init {
        NativeDiscovery().discover()
    }

    @Composable
    override fun playVideo(videoKey: String) {
        val videoUrl = "https://www.youtube.com/watch?v=$videoKey"

        var mediaPlayerComponent by remember { mutableStateOf<EmbeddedMediaPlayerComponent?>(null) }

        DisposableEffect(Unit) {
            val player = EmbeddedMediaPlayerComponent()
            mediaPlayerComponent = player

            onDispose {
                player.release()
                mediaPlayerComponent = null
            }
        }

        // Create and render the video player
        SwingPanel(
            factory = {
                JPanel().apply {
                    layout = BorderLayout()
                    mediaPlayerComponent?.let { add(it, BorderLayout.CENTER) }
                    SwingUtilities.invokeLater {
                        mediaPlayerComponent?.mediaPlayer()?.media()?.play(videoUrl)
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}


