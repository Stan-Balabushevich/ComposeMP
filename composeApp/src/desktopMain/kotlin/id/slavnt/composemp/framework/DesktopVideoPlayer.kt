package id.slavnt.composemp.framework

import androidx.compose.runtime.Composable
import id.slavnt.composemp.framework.video.VideoPlayer
import java.awt.Desktop
import java.net.URI

class DesktopVideoPlayer : VideoPlayer {



    // As an alternative, we can use the system browser to open YouTube URLs.
    @Composable
    override fun playVideo(videoKey: String) {
        val youtubeUrl = "https://www.youtube.com/watch?v=$videoKey"
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(URI(youtubeUrl))
        } else {
            println("Desktop or browse action not supported")
        }
    }


//
//    init {
//        NativeDiscovery().discover()
//    }
//
//    @Composable
//    override fun playVideo(videoKey: String) {
//        val videoUrl = "https://www.youtube.com/watch?v=$videoKey"
//
//        var mediaPlayerComponent by remember { mutableStateOf<EmbeddedMediaPlayerComponent?>(null) }
//
//        DisposableEffect(Unit) {
//            val player = EmbeddedMediaPlayerComponent()
//            mediaPlayerComponent = player
//
//            onDispose {
//                player.release()
//                mediaPlayerComponent = null
//            }
//        }
//
//        // Create and render the video player
//        SwingPanel(
//            factory = {
//                JPanel().apply {
//                    layout = BorderLayout()
//                    mediaPlayerComponent?.let { add(it, BorderLayout.CENTER) }
//                    SwingUtilities.invokeLater {
//                        mediaPlayerComponent?.mediaPlayer()?.media()?.play(videoUrl)
//                    }
//                }
//            },
//            modifier = Modifier.fillMaxSize()
//        )
//    }
}


