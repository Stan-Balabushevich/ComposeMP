package id.slavnt.composemp.presentation.detailscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import id.slavnt.composemp.domain.models.ReviewModel

@Composable
fun ReviewItem(review: ReviewModel, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {

        AsyncImage(
            model = "$BASE_IMAGE_URL${review.authorDetails.avatarPath}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.size(100.dp).padding(8.dp)
        )

        Text(text = review.author, style = MaterialTheme.typography.subtitle1)
        Text(text = review.content, style = MaterialTheme.typography.body2)
    }
}


@Composable
fun ReviewDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    reviews: List<ReviewModel> = emptyList(),
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

                    if (reviews.isEmpty()) {
                        Text(
                            text = "No reviews available.",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    } else{
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(22.dp)
                        ) {
                            items(reviews) { review ->
                                ReviewItem(review)
                            }
                        }
                    }
                }
            }
        }
    }
}
