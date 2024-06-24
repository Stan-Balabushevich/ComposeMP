package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import java.io.File

fun getPeopleDatabase(): PeopleDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "people.db")
    return Room.databaseBuilder<PeopleDatabase>(
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}
