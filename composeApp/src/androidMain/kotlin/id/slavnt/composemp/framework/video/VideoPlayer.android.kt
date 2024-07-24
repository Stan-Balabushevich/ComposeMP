package id.slavnt.composemp.framework.video

import id.slavnt.composemp.framework.AndroidVideoPlayer

actual fun createVideoPlayer(): VideoPlayer =
    AndroidVideoPlayer()