package com.example.projectzulfa.model





import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pemasukan")
data class Pemasukan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val nominal: Long
)
