package com.example.projectzulfa.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectzulfa.entitas.Pengeluaran

@Dao
interface PengeluaranDao {
    @Query("SELECT * FROM pengeluaran ORDER BY tanggal DESC")
    fun getAllPengeluaran(): LiveData<List<Pengeluaran>>



    @Query("SELECT SUM(nominal) FROM pengeluaran")
    fun getTotalPengeluaran(): LiveData<Long>

    @Insert
    suspend fun insertPengeluaran(pengeluaran: Pengeluaran)

    @Update
    suspend fun updatePengeluaran(pengeluaran: Pengeluaran)

    @Delete
    suspend fun deletePengeluaran(pengeluaran: Pengeluaran)
}