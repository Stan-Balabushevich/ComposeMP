package id.slavnt.composemp.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

fun getMovieDatabase(context: Context): MovieDatabase {
    val dbFile = context.getDatabasePath("people.db")
    return Room.databaseBuilder<MovieDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .fallbackToDestructiveMigration(false)
        .setDriver(BundledSQLiteDriver())
        .build()
}