package id.slavnt.composemp.framework.video

import androidx.compose.runtime.Composable

interface VideoPlayer {
    @Composable
    fun playVideo(videoKey: String)
}

expect fun createVideoPlayer(): VideoPlayer