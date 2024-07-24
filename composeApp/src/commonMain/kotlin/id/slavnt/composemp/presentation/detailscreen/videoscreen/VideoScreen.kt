package id.slavnt.composemp.presentation.detailscreen.videoscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.slavnt.composemp.presentation.detailscreen.DetailMovieViewModel
import id.slavnt.composemp.presentation.detailscreen.components.VideoDialog
import id.slavnt.composemp.presentation.navigation.Screen

@Composable
fun VideoScreen(
    navController: NavController,
    viewModel: DetailMovieViewModel
) {

    val videos by viewModel.movieVideos.collectAsState()

    var showVideoDialog by remember { mutableStateOf(false) }
    var selectedVideoKey by remember { mutableStateOf("") }

    VideoDialog(
        showDialog = showVideoDialog,
        onDismiss = { showVideoDialog = false },
        videoKey = selectedVideoKey

    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movie videos") },
                navigationIcon = {
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
                }
            )
        }
    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 46.dp)) {

            if (videos.isEmpty()) {
                Text(
                    text = "No videos available.",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(videos) { video ->
                    VideoItem(
                        video = video,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        onItemClick = { videoKey ->
                            showVideoDialog = true
                            selectedVideoKey = videoKey

                        }
                    )
                }
            }
        }

    }
}
