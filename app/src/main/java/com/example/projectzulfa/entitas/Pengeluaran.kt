package com.example.projectzulfa.entitas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pengeluaran")
data class Pengeluaran(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val nominal: Long,
    val tanggal: Long = System.currentTimeMillis()
)