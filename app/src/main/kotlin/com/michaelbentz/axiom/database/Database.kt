package com.michaelbentz.axiom.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.michaelbentz.axiom.DATABASE_NAME
import com.michaelbentz.axiom.database.dao.PlaceholderDao
import com.michaelbentz.axiom.database.entity.PlaceholderEntity

@androidx.room.Database(
    entities = [
        PlaceholderEntity::class
    ],
    exportSchema = false,
    version = 1,
)
abstract class Database : RoomDatabase() {
    abstract fun placeholderDao(): PlaceholderDao

    companion object {

        @Volatile
        private var instance: Database? = null

        fun getInstance(context: Context): Database {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): Database {
            return Room.databaseBuilder(context, Database::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }
}