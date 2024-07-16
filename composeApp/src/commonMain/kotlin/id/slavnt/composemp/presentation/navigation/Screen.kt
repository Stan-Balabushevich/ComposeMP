package id.slavnt.composemp.presentation.navigation

sealed class Screen(val route: String) {
    data object MovieListScreen: Screen("movie_list_screen")
    data object MovieDetailScreen: Screen("movie_detail_screen")
    data object FullImageScreen: Screen("full_image_screen")
}
