package com.example.projectzulfa.Database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projectzulfa.Dao.PengeluaranDao
import com.example.projectzulfa.entitas.Pengeluaran

@Database(entities = [Pengeluaran::class], version = 3)
abstract class AppDatabase2 : RoomDatabase() {
    abstract fun pengeluaranDao(): PengeluaranDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase2? = null

        fun getDatabase(context: Context): AppDatabase2 {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase2::class.java,
                    "app_database2"

                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}