package id.slavnt.composemp.presentation

import BatteryManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.slavnt.composemp.data.local.database.PeopleDatabase
import id.slavnt.composemp.data.local.database.db_object.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AppViewModel(private val db: PeopleDatabase, private val batteryManager: BatteryManager): ViewModel() {

    private val _personList = MutableStateFlow<List<Person>>(emptyList())
    val personList: StateFlow<List<Person>> = _personList

    private val _batteryLevel = MutableStateFlow(0)
    val batteryLevel: StateFlow<Int> = _batteryLevel



    init {
        getPeople()
        refreshBatteryLevel()
    }

    private fun getPeople(){
        viewModelScope.launch(Dispatchers.IO) {
             db.peopleDao().getAllPeople().collect{ people ->
                 if(people.isEmpty()){
                     val peopleList = listOf(
                         Person(name = "John"),
                         Person(name = "Alice"),
                         Person(name = "Philipp"),
                     )
                     peopleList.forEach {
                         db.peopleDao().upsert(it)
                     }
                 }
                 _personList.value = people
             }
        }
    }

    fun deletePerson(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            db.peopleDao().delete(person)
        }
    }

    private fun refreshBatteryLevel(){

        viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                _batteryLevel.value = batteryManager.getBatteryLevel()
                delay(60000) // Update every minute
            }
        }
    }

}