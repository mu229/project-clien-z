package com.example.projectzulfa.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectzulfa.Dao.PemasukanDao

class PemasukanViewModelFactory(private val pemasukanDao: PemasukanDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PemasukanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PemasukanViewModel(pemasukanDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
