package id.slavnt.composemp

import App
import BatteryManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import id.slavnt.composemp.database.getPeopleDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbDao =  getPeopleDatabase(applicationContext).peopleDao()

        setContent {


            App(batteryManager = BatteryManager(applicationContext), peopleDao = dbDao)
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AppAndroidPreview() {
//    App()
//}