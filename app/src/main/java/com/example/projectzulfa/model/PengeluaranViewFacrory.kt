package com.example.projectzulfa.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectzulfa.Dao.PengeluaranDao

class PengeluaranViewFacrory(private val pengeluaranDao: PengeluaranDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PengeluaranViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PengeluaranViewModel(pengeluaranDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}