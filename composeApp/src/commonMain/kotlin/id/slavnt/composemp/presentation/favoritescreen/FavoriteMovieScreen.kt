package id.slavnt.composemp.presentation.favoritescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.slavnt.composemp.common.Constants
import id.slavnt.composemp.presentation.mainscreen.MainScreenViewModel
import id.slavnt.composemp.presentation.mainscreen.components.MovieItem
import id.slavnt.composemp.presentation.navigation.Screen

@Composable
fun FavoriteMovieScreen(
    navController: NavController,
    viewModel: MainScreenViewModel
){

    val favoriteMovies by viewModel.favoriteMovies.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 46.dp)
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

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Favorite Movies",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            items(favoriteMovies) { movie ->
                MovieItem(
                    movie = movie,
                    columnModifier = Modifier.width(300.dp),
                    imageModifier = Modifier.height(400.dp),
                    onItemClick = { movieDetail ->
                        navController.navigate(
                            Screen.MovieDetailScreen.route
                                    + "?${Constants.MOVIE_ID}=${movieDetail.id}")
                    },
                    onFavoriteClick = { favMovie ->
                        viewModel.toggleFavorite(favMovie)
                        viewModel.removeFavoriteMovie(favMovie)
                    }
                )
            }
        }

    }

}