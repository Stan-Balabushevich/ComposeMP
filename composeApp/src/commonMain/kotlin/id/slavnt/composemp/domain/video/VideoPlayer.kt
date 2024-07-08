package id.slavnt.composemp.domain.video

import androidx.compose.runtime.Composable

interface VideoPlayer {
    @Composable
    fun playVideo(videoKey: String)
}