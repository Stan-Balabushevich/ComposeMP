package id.slavnt.composemp.data.local.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getMovieDatabase(): MovieDatabase {
    val dbFile = NSHomeDirectory() + "/people.db"
    return Room.databaseBuilder<MovieDatabase>(
        name = dbFile,
        factory = { MovieDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}