package com.aradevs.room_example.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aradevs.guia4_1.db.DatabaseDao
import com.aradevs.guia4_1.db.PersonEntity

@Database(
    entities = [PersonEntity::class], //array of entities
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDatabaseDao(): DatabaseDao

    companion object {
        private const val DATABASE_NAME = "app_db"

        @Synchronized
        fun getDatabase(context: Context): AppDatabase = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}