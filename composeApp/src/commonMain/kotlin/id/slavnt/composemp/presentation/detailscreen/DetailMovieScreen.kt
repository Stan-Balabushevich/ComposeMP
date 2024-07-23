package id.slavnt.composemp.presentation.detailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import id.slavnt.composemp.common.Constants.BASE_IMAGE_URL
import id.slavnt.composemp.domain.models.MovieCastModel
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.domain.models.MovieImageModel
import id.slavnt.composemp.domain.models.ReviewModel
import id.slavnt.composemp.domain.models.toMovieItem
import id.slavnt.composemp.presentation.detailscreen.components.CastDialog
import id.slavnt.composemp.presentation.detailscreen.components.ClickableText
import id.slavnt.composemp.presentation.detailscreen.components.DialogButton
import id.slavnt.composemp.presentation.detailscreen.components.ImagesDialog
import id.slavnt.composemp.presentation.detailscreen.components.ReviewDialog
import id.slavnt.composemp.presentation.mainscreen.MainScreenViewModel
import id.slavnt.composemp.presentation.navigation.Screen
import org.koin.compose.koinInject

@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: DetailMovieViewModel = koinInject(),
    // Both screens have to use same view model instance to retain state of search query and page number
    viewModelMain: MainScreenViewModel,
    navController: NavController
) {

    LaunchedEffect(movieId) {
        viewModel.setMovieId(movieId)
    }

    val movieDetail by viewModel.movieDetail.collectAsState()
    val review by viewModel.movieReviews.collectAsState()
    val cast by viewModel.movieCredits.collectAsState()
    val images by viewModel.movieImages.collectAsState()

    movieDetail?.let { detail ->
        DetailScreen(
            movieDetail = detail,
            navController = navController,
            cast = cast,
            images = images,
            reviews = review?.reviews ?: emptyList(),
            onFavoriteClick = { movieDetail ->
                viewModel.toggleFavorite(movieDetail.toMovieItem())
                viewModelMain.toggleFavorite(movieDetail.toMovieItem())
            }
        )
    } ?: run {
        Text(text = "Loading...")
    }


}

@Composable
fun DetailScreen(
    movieDetail: MovieDetailModel,
    navController: NavController,
    cast: List<MovieCastModel> = emptyList(),
    images: List<MovieImageModel> = emptyList(),
    reviews: List<ReviewModel> = emptyList(),
    onFavoriteClick: (MovieDetailModel) -> Unit
) {

    val uriHandler = LocalUriHandler.current

    var showImagesDialog by remember { mutableStateOf(false) }
    var showCastDialog by remember { mutableStateOf(false) }
    var showReviewDialog by remember { mutableStateOf(false) }

    CastDialog(
        showDialog = showCastDialog,
        onDismiss = { showCastDialog = false },
        cast = cast
    )

    ImagesDialog(
        showDialog = showImagesDialog,
        onDismiss = { showImagesDialog = false },
        images = images,
        onImageClick = {
//            imageUrl ->
//            navController.navigate(Screen.FullImageScreen.route + "?${Constants.BASE_IMAGE_URL}=$imageUrl")
        }
    )

    ReviewDialog(
        showDialog = showReviewDialog,
        onDismiss = { showReviewDialog = false },
        reviews = reviews
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 46.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                // To avoid popBackStack() to blank screen
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                } else {
                    // Handle the case when there is no back stack entry
                    // For example, navigate to a specific screen or show a message
                    navController.navigate(Screen.MovieListScreen.route) {
                        popUpTo(Screen.MovieListScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }


            IconButton(onClick = {
                onFavoriteClick(movieDetail)
            }) {

                Icon(
                    imageVector = if (movieDetail.favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = if (movieDetail.favorite) "Remove from favorites" else "Add to favorites"
                )
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(Modifier.fillMaxSize()) {

            item { AsyncImage(
                model = "$BASE_IMAGE_URL${movieDetail.posterPath}",
                contentDescription = movieDetail.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(300.dp)
                    .height(400.dp),
            ) }

            item {

                Row(modifier = Modifier.fillMaxWidth()) {

                    DialogButton(text = "Cast", onClick = { showCastDialog = true })
                    Spacer(modifier = Modifier.width(8.dp))
                    DialogButton(text = "Images", onClick = { showImagesDialog = true })
                    Spacer(modifier = Modifier.width(8.dp))
                    DialogButton(text = "Reviews", onClick = { showReviewDialog = true })

                }
            }

            item {
                SelectionContainer {
                    Text(
                        text = movieDetail.title,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            // can be deleted after testing
            item {
                SelectionContainer {
                    Text(
                        text = movieDetail.id.toString(),
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) }
            }

            item {

                ClickableText(
                    text = movieDetail.homepage,
                    onClick = { movieDetail.homepage.let { uriHandler.openUri(it) } },
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

            }

            item {

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Tagline: ")
                        }
                        append(movieDetail.tagline)
                    },
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item { Text(
                text =  buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Original Title: ")
                    }
                    append(movieDetail.originalTitle)
                },
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            ) }
            item { Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Release Date: ")
                    }
                    append(movieDetail.releaseDate)
                } ,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            }
            item { Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Overview: ")
                    }
                    append(movieDetail.overview)
                } ,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            ) }

            item { Text(
                text =  buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Genres: ")
                    }
                    append(movieDetail.genres.joinToString(", "))
                } ,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            ) }

            item {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Production Companies: ")
                        }
                        append(movieDetail.productionCompanies.joinToString(", "))
                    } ,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Production Countries: ")
                        }
                        append(movieDetail.productionCountries.joinToString(", ") )
                    }   ,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            item {

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Spoken Languages: ")
                        }
                        append(movieDetail.spokenLanguages.joinToString(", "))
                    }  ,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

            }
            item { Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Original language: ")
                    }
                    append(movieDetail.originalLanguage)
                } ,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            ) }
            item { Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Popularity: ")
                    }
                    append("${movieDetail.popularity}")
                } ,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            ) }
            item {  Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Vote Average: ")
                    }
                    append("${movieDetail.voteAverage}")
                } ,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            ) }
            item {  Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Vote Count: ")
                    }
                    append("${movieDetail.voteCount}")
                }  ,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            ) }
            item { Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Status: ")
                    }
                    append(movieDetail.status)
                } ,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            ) }
            item {  Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Budget: ")
                    }
                    append("$${movieDetail.budget}")
                } ,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            ) }
            item { Text(
                text =  buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Revenue: ")
                    }
                    append("$${movieDetail.revenue}")
                } ,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            ) }

            item {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Runtime: ")
                        }
                        append("${movieDetail.runtime} minutes")
                    } ,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

//            item {
//                VideoButton(
//                    hasVideo = true,
////                    hasVideo = movieDetail.video,
//                    onClick = {
//                        showImagesDialog = true
//                    }
//                )
//            }


        }

    }

}