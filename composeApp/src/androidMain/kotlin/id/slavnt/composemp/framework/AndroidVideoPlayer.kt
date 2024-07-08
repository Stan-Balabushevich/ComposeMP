package id.slavnt.composemp.framework

import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import id.slavnt.composemp.domain.video.VideoPlayer

class AndroidVideoPlayer : VideoPlayer {
    @Composable
    override fun playVideo(videoKey: String) {
        val youtubeUrl = "https://www.youtube.com/embed/$videoKey"
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.pluginState = WebSettings.PluginState.ON
                    webChromeClient = WebChromeClient()
                    webViewClient = WebViewClient()
                    loadUrl(youtubeUrl)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
