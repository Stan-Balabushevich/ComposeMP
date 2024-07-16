package id.slavnt.composemp.presentation.detailscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import id.slavnt.composemp.common.Constants.BASE_IMAGE_URL

@Composable
fun ImageItem(
    imagePath: String,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {
    AsyncImage(
        model = "$BASE_IMAGE_URL${imagePath}",
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.size(160.dp).padding(8.dp).clickable { onItemClick(imagePath) }
    )
}

@Composable
fun ImageGrid(images: List<String>, onImageClick: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(images) { imageUrl ->
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { onImageClick(imageUrl) }
                    .aspectRatio(1f)
            )
        }
    }
}

