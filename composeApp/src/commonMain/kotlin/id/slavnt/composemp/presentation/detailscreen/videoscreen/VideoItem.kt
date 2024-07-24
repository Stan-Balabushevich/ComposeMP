package id.slavnt.composemp.presentation.detailscreen.videoscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.slavnt.composemp.domain.models.MovieVideoModel

@Composable
fun VideoItem(
    video: MovieVideoModel,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
                .clickable { onItemClick(video.key) }
        ){
            Text(
                text = video.name,
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = video.site,
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = video.type,
                modifier = Modifier.fillMaxSize()
            )
        Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = video.publishedAt,
                modifier = Modifier.fillMaxSize()
            )
        Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${video.size}p",
                modifier = Modifier.fillMaxSize()
            )

        }
    }
}
