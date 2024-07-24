package id.slavnt.composemp.framework

import platform.WebKit.WKWebView
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.UIKitView
import id.slavnt.composemp.framework.video.VideoPlayer

class IOSVideoPlayer : VideoPlayer {
    @Composable
    override fun playVideo(videoKey: String) {
        val youtubeUrl = "https://www.youtube.com/embed/$videoKey"
        UIKitView(
            factory = {
                val webView = WKWebView(frame = CGRectZero)
                webView.loadRequest(NSURLRequest(URL = NSURL(string = youtubeUrl)))
                webView
            },
            update = { _ -> }
        )
    }
}
