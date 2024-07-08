package id.slavnt.composemp.presentation.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import id.slavnt.composemp.domain.models.MovieMainItem
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun MovieScreen(viewModel: MainScreenViewModel = koinInject()) {
    var searchQuery by remember { mutableStateOf("") }

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
        MovieSection(title = "Popular Movies", movies = viewModel.popularMovies.collectAsState().value)
        Spacer(modifier = Modifier.height(16.dp))
        CategoriesSection(viewModel = viewModel)
    }
}

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit, onSearch: () -> Unit) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(8.dp)
            .background(Color.LightGray, shape = MaterialTheme.shapes.small),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() })
    )
}

//@Composable
//fun MovieSection(title: String, movies: List<MovieMainItem>) {
//    Column {
//        Text(text = title, style = MaterialTheme.typography.h6)
//        Spacer(modifier = Modifier.height(8.dp))
//        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//
//            items(movies){
//                MovieItem(movie = it)
//            }
//
//        }
//    }
//}

@Composable
fun CategoriesSection(viewModel: MainScreenViewModel) {
    Column {
        Text(text = "Categories", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CategoryItem("Popular")
            CategoryItem("Top Rated")
            // Add more categories as needed
        }
        Spacer(modifier = Modifier.height(8.dp))
        MovieSection(title = "Top Rated Movies", movies = viewModel.topRatedMovies.collectAsState().value)
    }
}

@Composable
fun MovieItem(movie: MovieMainItem) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    val fullPosterUrl = "$baseUrl${movie.posterPath}"

    Column(
        modifier = Modifier
            .width(100.dp)
    ) {
        AsyncImage(
            model = fullPosterUrl,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(150.dp)
        )
        Text(text = movie.title, maxLines = 1)
        Text(text = "${movie.voteAverage}", style = MaterialTheme.typography.body2)
    }
}


@Composable
fun MovieSection(title: String, movies: List<MovieMainItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))

        val scrollState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        LazyRow(
            state = scrollState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .scrollable(
                    orientation = Orientation.Horizontal,
                    state = rememberScrollableState { delta ->
                        coroutineScope.launch {
                            scrollState.scrollBy(-delta)
                        }
                        delta
                    }
                )
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie)
            }
        }
    }
}


@Composable
fun CategoryItem(category: String) {
    Text(
        text = category,
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Gray, shape = MaterialTheme.shapes.small)
            .padding(8.dp)
    )
}
