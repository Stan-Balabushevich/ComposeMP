package id.slavnt.composemp.presentation.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.slavnt.composemp.data.remote.dt_object.Movies
import id.slavnt.composemp.data.remote.dt_object.toMovieItem
import id.slavnt.composemp.domain.models.MovieMainItem
import org.koin.compose.koinInject


@Composable
fun MovieScreenDesktop(viewModel: MainScreenViewModel = koinInject()) {
    var searchQuery by remember { mutableStateOf("") }

    val popularMovies by viewModel.popularMovies.collectAsState()
    val topRatedMovies by viewModel.topRatedMovies.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChanged = { searchQuery = it },
            onSearch = { /* Handle search */ }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MovieSectionDesktop(
                title = "Popular Movies",
                sectionData = popularMovies,
                modifier = Modifier.weight(1f),
                onNextPage = { viewModel.loadNextPopularPage() },
                onPreviousPage = { viewModel.loadPreviousPopularPage() }
            )
            MovieSectionDesktop(
                title = "Top Rated Movies",
                sectionData = topRatedMovies,
                modifier = Modifier.weight(1f),
                onNextPage = { viewModel.loadNextTopRatedPage() },
                onPreviousPage = { viewModel.loadPreviousTopRatedPage() }
            )
        }
    }
}

@Composable
fun MovieSectionDesktop(
    title: String,
    sectionData: Movies,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Page ${sectionData.page} of ${sectionData.totalPages}",
            style = MaterialTheme.typography.body2
        )
        Text(
            text = "Total Results: ${sectionData.totalResults}",
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(8.dp))

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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(sectionData.results) { movie ->
                MovieItem(
                    movie = movie.toMovieItem(),
                    columnModifier = Modifier.width(300.dp),
                    imageModifier = Modifier.height(400.dp)
                )
            }
        }
    }
}
