package id.slavnt.composemp.presentation.detailscreen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VideoButton(hasVideo: Boolean, onClick: () -> Unit) {
    Button(
        onClick = { if (hasVideo) onClick() },
        enabled = hasVideo,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (hasVideo) MaterialTheme.colors.primary else Color.Gray
        ),
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Text(text = if (hasVideo) "Watch Video" else "No Video Available")
    }
}
