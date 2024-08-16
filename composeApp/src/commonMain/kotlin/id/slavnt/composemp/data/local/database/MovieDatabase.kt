package id.slavnt.composemp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.slavnt.composemp.data.local.database.db_object.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 3
)
abstract class MovieDatabase: RoomDatabase(), DB {

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