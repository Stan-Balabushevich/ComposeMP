package id.slavnt.composemp.presentation.mainscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import id.slavnt.composemp.common.Constants.BASE_IMAGE_URL
import id.slavnt.composemp.domain.models.MovieMainItem

@Composable
fun MovieItem(
    movie: MovieMainItem,
    columnModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    onItemClick: (MovieMainItem) -> Unit
) {
//    val baseUrl = "https://image.tmdb.org/t/p/w500"
    val fullPosterUrl = "$BASE_IMAGE_URL${movie.posterPath}"

    var imageState by remember { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty) }

    Column(
        modifier = columnModifier
            .clickable{
                onItemClick(movie)
            }
    ) {
        AsyncImage(
            model = fullPosterUrl,
            onState = { imageState = it },
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = imageModifier,
        )

//        when (imageState) {
//            is AsyncImagePainter.State.Loading -> {
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//            }
//            is AsyncImagePainter.State.Error -> {
//                Text(
//                    text = "Error loading image",
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//            }
//            else -> Unit
//        }

        Text(text = movie.title, maxLines = 2, style = MaterialTheme.typography.body1)
        Text(text = "Rate: ${movie.voteAverage}", style = MaterialTheme.typography.body1)
    }
}
