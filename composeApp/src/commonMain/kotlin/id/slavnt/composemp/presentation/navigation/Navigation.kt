package id.slavnt.composemp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.slavnt.composemp.common.Constants
import id.slavnt.composemp.presentation.detailscreen.fullimagescreen.FullScreenImage
import id.slavnt.composemp.presentation.detailscreen.MovieDetailScreen
import id.slavnt.composemp.presentation.favoritescreen.FavoriteMovieScreen
import id.slavnt.composemp.presentation.mainscreen.MainScreenViewModel
import id.slavnt.composemp.presentation.mainscreen.MovieScreenDesktop
import id.slavnt.composemp.presentation.mainscreen.MovieScreenMobile
import org.koin.compose.koinInject


@Composable
fun NavigationMobile() {

    // Both screens have to use same view model instance to retain state of search query and page number
    val viewModel: MainScreenViewModel = koinInject()

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MovieListScreen.route
    ) {

        composable(route = Screen.MovieListScreen.route) {
            MovieScreenMobile(navController = navController, viewModel = viewModel)
        }

        composable(route = Screen.FavoriteMovieScreen.route) {
            FavoriteMovieScreen(navController = navController, viewModel = viewModel)
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
            MovieDetailScreen(movieId = movieId, navController = navController, viewModelMain = viewModel)

        }
    }
}


@Composable
fun NavigationDesktop() {

    val viewModel: MainScreenViewModel = koinInject()

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MovieListScreen.route
    ) {
        composable(route = Screen.MovieListScreen.route) {
            MovieScreenDesktop(navController = navController, viewModel = viewModel)
        }

        composable(route = Screen.FavoriteMovieScreen.route) {

            FavoriteMovieScreen(navController = navController, viewModel = viewModel)

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
            MovieDetailScreen(movieId = movieId, navController = navController, viewModelMain = viewModel)

        }

        composable(
            route = Screen.FullImageScreen.route + "?${Constants.BASE_IMAGE_URL}={${Constants.BASE_IMAGE_URL}}",
            arguments = listOf(
                navArgument(
                    name = Constants.BASE_IMAGE_URL
                ) {
                    type = NavType.StringType
                })
        ) { backStackEntry ->

            val imageUrl = backStackEntry.arguments?.getString(Constants.BASE_IMAGE_URL) ?: ""
            FullScreenImage(imageUrl = imageUrl) {
                navController.popBackStack()
            }
        }
    }
}

