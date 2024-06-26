package id.slavnt.composemp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import presentation.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            App()
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AppAndroidPreview() {
//    App()
//}