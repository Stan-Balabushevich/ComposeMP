
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF2196F3),         // Calm blue
    primaryVariant = Color(0xFF1976D2),  // Darker blue
    secondary = Color(0xFF03A9F4),       // Soft blue
    background = Color(0xFF121212),      // Dark background
    surface = Color(0xFF121212),         // Dark surface
    error = Color(0xFFF44336),           // Soft red
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF2196F3),         // Calm blue
    primaryVariant = Color(0xFF1976D2),  // Darker blue
    secondary = Color(0xFF03A9F4),       // Soft blue
    background = Color(0xFFF5F5F5),      // Light gray background
    surface = Color(0xFFFFFFFF),         // White surface
    error = Color(0xFFF44336),           // Soft red
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)


@Composable
fun MyComposeAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}
