package id.slavnt.composemp.framework.video

import id.slavnt.composemp.framework.IOSVideoPlayer

actual fun createVideoPlayer(): VideoPlayer =
    IOSVideoPlayer()