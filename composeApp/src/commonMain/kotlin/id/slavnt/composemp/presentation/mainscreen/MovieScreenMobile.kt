package id.slavnt.composemp.presentation.mainscreen

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.slavnt.composemp.common.Constants
import id.slavnt.composemp.data.remote.dt_object.Movies
import id.slavnt.composemp.data.remote.dt_object.toMovieItem
import id.slavnt.composemp.presentation.mainscreen.components.MovieItem
import id.slavnt.composemp.presentation.mainscreen.components.SearchBar
import id.slavnt.composemp.presentation.navigation.Screen

@Composable
fun MovieScreenMobile(
    viewModel: MainScreenViewModel,
    navController: NavController
) {
    val searchQuery by viewModel.searchQuery.collectAsState()

    val popularMovies by viewModel.popularMovies.collectAsState()
    val topRatedMovies by viewModel.topRatedMovies.collectAsState()
    val searchResult by viewModel.searchResult.collectAsState()
    val upcomingMovies by viewModel.upcomingMovies.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChanged = { viewModel.setSearchQuery(it) },
            onSearch = { viewModel.onSearchQueryChanged(searchQuery,1) }
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){

            if (searchQuery.isNotEmpty() && searchResult.results.isNotEmpty()) {

                item {
                    MovieSectionMobile(
                        title = "Search Results",
                        sectionData = searchResult,
                        onNextPage = {
                            viewModel.loadNextSearchPage(searchQuery)
                        },
                        onPreviousPage = {
                            viewModel.loadPreviousSearchPage(searchQuery)
                        },
                        navController = navController
                    )
                }



            } else{

                item {
                    MovieSectionMobile(
                        title = "Popular Movies",
                        sectionData = popularMovies,
                        onNextPage = {
                            viewModel.loadNextPopularPage()
                        },
                        onPreviousPage = {
                            viewModel.loadPreviousPopularPage()
                        },
                        navController = navController
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    MovieSectionMobile(
                        title = "Upcoming Movies",
                        sectionData = upcomingMovies,
                        onNextPage = {
                            viewModel.loadNextUpcomingPage()
                        },
                        onPreviousPage = {
                            viewModel.loadPreviousUpcomingPage()
                        },
                        navController = navController
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    MovieSectionMobile(
                        title = "Top Rated Movies",
                        sectionData = topRatedMovies,
                        onNextPage = {
                            viewModel.loadNextTopRatedPage()
                                     },
                        onPreviousPage = {
                            viewModel.loadPreviousTopRatedPage()
                                         },
                        navController = navController
                    )
                }
            }
        }
    }
}



@Composable
fun MovieSectionMobile(
    title: String,
    sectionData: Movies,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column {
        Text(text = title, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Page ${sectionData.page} of ${sectionData.totalPages}",
            style = MaterialTheme.typography.body2
        )
        Text(
            text = "Total Results: ${sectionData.totalResults}",
            style = MaterialTheme.typography.body2
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onPreviousPage) {
                Text("Previous Page")
            }
            Button(onClick = onNextPage) {
                Text("Next Page")
            }
        }
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(sectionData.results){
                MovieItem(
                    movie = it.toMovieItem(),
                    columnModifier = Modifier.width(100.dp),
                    imageModifier = Modifier.height(150.dp),
                    onItemClick = { movie ->
                        navController.navigate(
                            Screen.MovieDetailScreen.route
                                    + "?${Constants.MOVIE_ID}=${movie.id}")
                    }
                )
            }

        }
    }
}