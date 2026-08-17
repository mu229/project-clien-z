package com.example.projectzulfa.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.projectzulfa.entitas.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE title LIKE :searchQuery OR content LIKE :searchQuery")
    fun searchNotes(searchQuery: String): List<Note>

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}