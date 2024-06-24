import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import database.PeopleDao
import database.Person
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    batteryManager: BatteryManager,
    peopleDao: PeopleDao
) {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }


        val people by peopleDao.getAllPeople().collectAsState(initial = emptyList())
        val scope = rememberCoroutineScope()

        LaunchedEffect(true) {
            val peopleList = listOf(
                Person(name = "John"),
                Person(name = "Alice"),
                Person(name = "Philipp"),
            )
            peopleList.forEach {
                peopleDao.upsert(it)
            }
        }


        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                val batteryLevel = remember { batteryManager.getBatteryLevel() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                    Text("Battery level: $batteryLevel")
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    items(people) { person ->
                        Text(
                            text = person.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    scope.launch {
                                        peopleDao.delete(person)
                                    }
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}