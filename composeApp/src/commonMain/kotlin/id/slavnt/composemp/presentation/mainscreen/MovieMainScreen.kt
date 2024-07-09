package id.slavnt.composemp.presentation.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import id.slavnt.composemp.data.remote.dt_object.Movies
import id.slavnt.composemp.data.remote.dt_object.toMovieItem
import id.slavnt.composemp.domain.models.MovieMainItem
import org.koin.compose.koinInject

@Composable
fun MovieScreen(viewModel: MainScreenViewModel = koinInject()) {
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
        MovieSection(title = "Popular Movies", sectionData = popularMovies, onNextPage = {
            viewModel.loadNextPopularPage()
        }, onPreviousPage = {
            viewModel.loadPreviousPopularPage()
        })
        Spacer(modifier = Modifier.height(16.dp))
        CategoriesSection(topRatedMovies, onNextPage = {
            viewModel.loadNextTopRatedPage()
        }, onPreviousPage = {
            viewModel.loadPreviousTopRatedPage()
        })
    }
}

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit, onSearch: () -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 4.dp),
        placeholder = { Text(text = "Search") },
        singleLine = true,
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray,
            cursorColor = Color.Black
        ),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Start
        )
    )
}

@Composable
fun MovieSection(title: String,
                 sectionData: Movies,
                 onNextPage: () -> Unit,
                 onPreviousPage: () -> Unit,
                 modifier: Modifier = Modifier) {
    Column {
        Text(text = title, style = MaterialTheme.typography.h6)
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
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(sectionData.results){
                MovieItem(
                    movie = it.toMovieItem(),
                    columnModifier = Modifier.width(100.dp),
                    imageModifier = Modifier.height(150.dp)
                )
            }

        }
    }
}

@Composable
fun CategoriesSection(movies: Movies,onNextPage: () -> Unit,
                      onPreviousPage: () -> Unit,) {
    Column {
        Text(text = "Categories", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CategoryItem("Popular")
            CategoryItem("Top Rated")
            // Add more categories as needed
        }
        Spacer(modifier = Modifier.height(8.dp))
        MovieSection(title = "Top Rated Movies", sectionData = movies, onNextPage = {
            onNextPage()
        }, onPreviousPage = {
            onPreviousPage()
        })
    }
}

@Composable
fun MovieItem(movie: MovieMainItem, columnModifier: Modifier = Modifier, imageModifier: Modifier = Modifier) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    val fullPosterUrl = "$baseUrl${movie.posterPath}"

    var imageState by remember { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty) }

    Column(
        modifier = columnModifier
    ) {
        AsyncImage(
            model = fullPosterUrl,
            onState = { imageState = it },
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = imageModifier,
        )

//        when (imageState) {
//            is AsyncImagePainter.State.Loading -> {
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//            }
//            is AsyncImagePainter.State.Error -> {
//                Text(
//                    text = "Error loading image",
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//            }
//            else -> Unit
//        }

        Text(text = movie.title, maxLines = 2, style = MaterialTheme.typography.body1)
        Text(text = "Rate: ${movie.voteAverage}", style = MaterialTheme.typography.body1)
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
