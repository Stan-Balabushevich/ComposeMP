package id.slavnt.composemp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.slavnt.composemp.common.Constants
import id.slavnt.composemp.presentation.detailscreen.MovieDetailScreen
import id.slavnt.composemp.presentation.mainscreen.MovieScreenDesktop
import id.slavnt.composemp.presentation.mainscreen.MovieScreenMobile
import kotlinx.serialization.Serializable


@Serializable
object MainMovieScreen

@Serializable
data class DetailMovieScreen(
    val id: String
)

@Composable
fun NavigationMobile() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MovieListScreen.route
    ) {
        composable(route = Screen.MovieListScreen.route) {
            MovieScreenMobile(navController = navController)
        }
        composable(
            route = Screen.MovieDetailScreen.route + "?${Constants.MOVIE_ID}={${Constants.MOVIE_ID}}",
            arguments = listOf(
                navArgument(
                    name = Constants.MOVIE_ID
                ) {
                    type = NavType.IntType
                    defaultValue = 0
                })
        ) {

            val movieId = it.arguments?.getInt(Constants.MOVIE_ID) ?: 0
            MovieDetailScreen(movieId = movieId, navController = navController)

        }
    }
}


@Composable
fun NavigationDesktop() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MovieListScreen.route
    ) {
        composable(route = Screen.MovieListScreen.route) {
            MovieScreenDesktop(navController = navController)
        }

        composable(
            route = Screen.MovieDetailScreen.route + "?${Constants.MOVIE_ID}={${Constants.MOVIE_ID}}",
            arguments = listOf(
                navArgument(
                    name = Constants.MOVIE_ID
                ) {
                    type = NavType.IntType
                    defaultValue = 0
                })
        ) {

            val movieId = it.arguments?.getInt(Constants.MOVIE_ID) ?: 0
            MovieDetailScreen(movieId = movieId, navController = navController)

        }
    }
}

