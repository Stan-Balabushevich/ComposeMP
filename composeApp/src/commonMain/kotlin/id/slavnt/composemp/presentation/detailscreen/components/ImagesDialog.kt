package id.slavnt.composemp.presentation.detailscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import id.slavnt.composemp.common.Constants.BASE_IMAGE_URL
import id.slavnt.composemp.domain.models.MovieImageModel

@Composable
fun ImagesDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    images: List<MovieImageModel> = emptyList(),
    onImageClick: (String) -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = { onDismiss() }) {
                            Icon(Icons.Default.Close, contentDescription = "Close")
                        }
                    }

                    if (images.isEmpty()) {
                        Text(
                            text = "No images available.",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(images) { image ->
                            ImageItem(
                                imagePath = image.filePath,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(image.aspectRatio.toFloat()) // Assuming 16:9 aspect ratio for example
//                                    .height(300.dp)
                                    .padding(8.dp),
                                onItemClick = { onImageClick(image.filePath)}
                            )
                        }
                    }
                }
            }
        }
    }
}

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


