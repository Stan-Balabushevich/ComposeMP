package id.slavnt.composemp

import MyComposeAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import id.slavnt.composemp.presentation.navigation.NavigationMobile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyComposeAppTheme {
                NavigationMobile()
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AppAndroidPreview() {
//    App()
//}