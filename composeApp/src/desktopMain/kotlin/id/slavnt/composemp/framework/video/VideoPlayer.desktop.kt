package id.slavnt.composemp.framework.video

import id.slavnt.composemp.framework.DesktopVideoPlayer

actual fun createVideoPlayer(): VideoPlayer =
    DesktopVideoPlayer()