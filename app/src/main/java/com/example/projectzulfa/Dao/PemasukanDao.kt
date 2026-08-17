package com.example.projectzulfa.Dao

import androidx.room.*
import com.example.projectzulfa.model.Pemasukan

@Dao
interface PemasukanDao {
    @Query("SELECT * FROM pemasukan")
    suspend fun getAllPemasukan(): List<Pemasukan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPemasukan(pemasukan: Pemasukan)

    @Update
    suspend fun updatePemasukan(pemasukan: Pemasukan)

    @Delete
    suspend fun deletePemasukan(pemasukan: Pemasukan)

    @Query("SELECT * FROM pemasukan WHERE nama LIKE :searchQuery")
    suspend fun searchPemasukan(searchQuery: String): List<Pemasukan>

    @Query("SELECT SUM(nominal) FROM pemasukan")
    suspend fun getTotalPemasukan(): Long
}