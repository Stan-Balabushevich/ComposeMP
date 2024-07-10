package id.slavnt.composemp.presentation.detailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.slavnt.composemp.domain.models.MovieDetailModel
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = movieDetail.title,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Original Title: ${movieDetail.originalTitle}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Release Date: ${movieDetail.releaseDate}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Overview: ${movieDetail.overview}",
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Language: ${movieDetail.originalLanguage}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Popularity: ${movieDetail.popularity}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Vote Average: ${movieDetail.voteAverage}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Vote Count: ${movieDetail.voteCount}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Status: ${movieDetail.status}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Budget: $${movieDetail.budget}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Revenue: $${movieDetail.revenue}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}