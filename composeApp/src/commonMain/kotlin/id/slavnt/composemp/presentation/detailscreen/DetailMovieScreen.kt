package id.slavnt.composemp.presentation.detailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.presentation.detailscreen.components.ClickableText
import id.slavnt.composemp.presentation.detailscreen.components.VideoButton
import org.koin.compose.koinInject

@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: DetailMovieViewModel = koinInject(),
    navController: NavController
) {

    LaunchedEffect(movieId) {
        viewModel.setMovieId(movieId)
    }


    val movieDetail by viewModel.movieDetail.collectAsState()

    movieDetail?.let { detail ->
        DetailScreen(movieDetail = detail, navController)
    } ?: run {
        Text(text = "Loading...")
    }


}

@Composable
fun DetailScreen(movieDetail: MovieDetailModel,  navController: NavController) {

    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(Modifier.fillMaxSize()) {

            item { AsyncImage(
                model = "$BASE_IMAGE_URL${movieDetail.posterPath}",
                contentDescription = movieDetail.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(200.dp).height(300.dp),
            ) }
            item { Text(
                text = movieDetail.title,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 8.dp)
            ) }
item {

    SelectionContainer {
        Text(
            text = movieDetail.id.toString(),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 8.dp)
        ) }

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
                    append(movieDetail.genres.joinToString(", ") { it.name })
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
                        append(movieDetail.productionCompanies.joinToString(", ") { it.name })
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
                        append(movieDetail.productionCountries.joinToString(", ") { it.name })
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
                        append(movieDetail.spokenLanguages.joinToString(", ") { it.name })
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

            item {

                ClickableText(
                    text = movieDetail.homepage,
                    onClick = { movieDetail.homepage.let { uriHandler.openUri(it) } },
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

            }

            item {
                VideoButton(
                    hasVideo = movieDetail.video,
                    onClick = { /* Handle video button click */ }
                )
            }

        }

    }
}