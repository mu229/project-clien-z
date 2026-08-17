package com.example.projectzulfa.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projectzulfa.Dao.NoteDao
import com.example.projectzulfa.entitas.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase3 : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: AppDatabase3? = null

        fun getInstance(context: Context): AppDatabase3 {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase3::class.java,
                    "app_database3"
                ).build()
            }
            return instance!!
        }
    }
}