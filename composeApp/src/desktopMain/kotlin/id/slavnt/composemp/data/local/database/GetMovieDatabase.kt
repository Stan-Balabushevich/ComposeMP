package id.slavnt.composemp.data.local.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import java.io.File

fun getMovieDatabase(): MovieDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "people.db")
    return Room.databaseBuilder<MovieDatabase>(
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}
