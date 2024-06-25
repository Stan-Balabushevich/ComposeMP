package id.slavnt.composemp

import App
import BatteryManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import id.slavnt.composemp.database.getPeopleDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val dbDao = remember {
                getPeopleDatabase(applicationContext).peopleDao()
            }

            App(batteryManager = BatteryManager(applicationContext), peopleDao = dbDao)
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AppAndroidPreview() {
//    App()
//}