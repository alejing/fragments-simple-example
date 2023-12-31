package com.example.simplenavigationfragments.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simplenavigationfragments.database.user.UserDao
import com.example.simplenavigationfragments.database.user.Usuario

@Database(
    entities = [Usuario::class],
    version = 1,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun UserDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}