package com.example.projectzulfa.Database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projectzulfa.Dao.PemasukanDao
import com.example.projectzulfa.model.Pemasukan

@Database(entities = [Pemasukan::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pemasukanDao(): PemasukanDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Log.d("AppDatabase", "Creating database instance")
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                Log.d("AppDatabase", "Database instance created")
                instance
            }
        }
    }
}

