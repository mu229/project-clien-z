package com.example.projectzulfa.model

import androidx.lifecycle.*
import com.example.projectzulfa.Dao.PengeluaranDao
import com.example.projectzulfa.entitas.Pengeluaran
import kotlinx.coroutines.launch

class PengeluaranViewModel(private val pengeluaranDao: PengeluaranDao) : ViewModel() {
    val allPengeluaran: LiveData<List<Pengeluaran>> = pengeluaranDao.getAllPengeluaran()
    val totalPengeluaran: LiveData<Long> = pengeluaranDao.getTotalPengeluaran()



    fun insertPengeluaran(pengeluaran: Pengeluaran) = viewModelScope.launch {
        pengeluaranDao.insertPengeluaran(pengeluaran)
    }

    fun updatePengeluaran(pengeluaran: Pengeluaran) = viewModelScope.launch {
        pengeluaranDao.updatePengeluaran(pengeluaran)
    }

    fun deletePengeluaran(pengeluaran: Pengeluaran) = viewModelScope.launch {
        pengeluaranDao.deletePengeluaran(pengeluaran)
    }
}

