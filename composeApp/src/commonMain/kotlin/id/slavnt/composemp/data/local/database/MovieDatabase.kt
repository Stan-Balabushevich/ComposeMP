package id.slavnt.composemp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.slavnt.composemp.data.local.database.db_object.MovieEntity
import id.slavnt.composemp.data.local.database.db_object.Person

@Database(
    entities = [Person::class, MovieEntity::class],
    version = 2
)
abstract class MovieDatabase: RoomDatabase(), DB {

    abstract fun peopleDao(): PeopleDao

    abstract fun movieDao(): MovieDao

    override fun clearAllTables() {
        super.clearAllTables()
    }

}

// FIXME: Added a hack to resolve below issue:
// Class 'PeopleDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}