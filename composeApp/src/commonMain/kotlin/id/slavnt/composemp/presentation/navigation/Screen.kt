package id.slavnt.composemp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String = "", val icon: ImageVector? = null) {
    data object MovieListScreen: Screen("movie_list_screen", "Movies", Icons.Filled.Home)
    data object MovieDetailScreen: Screen("movie_detail_screen")
    data object FullImageScreen: Screen("full_image_screen")
    data object FavoriteMovieScreen: Screen("favorite_movie_screen", "Favorites", Icons.Filled.Favorite)
    data object VideoScreen: Screen("video_screen", "Videos")
}
