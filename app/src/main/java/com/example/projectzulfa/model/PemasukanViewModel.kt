package com.example.projectzulfa.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectzulfa.Dao.PemasukanDao
import kotlinx.coroutines.launch

class PemasukanViewModel(private val pemasukanDao: PemasukanDao) : ViewModel() {
    private val _allPemasukan = MutableLiveData<List<Pemasukan>>()
    val allPemasukan: LiveData<List<Pemasukan>> = _allPemasukan

    private val _totalPemasukan = MutableLiveData<Long>()
    val totalPemasukan: LiveData<Long> = _totalPemasukan

    init {
        loadAllPemasukan()
        updateTotalPemasukan()
    }

    private fun loadAllPemasukan() {
        viewModelScope.launch {
            _allPemasukan.value = pemasukanDao.getAllPemasukan()
        }
    }

    fun insertPemasukan(pemasukan: Pemasukan) {
        viewModelScope.launch {
            pemasukanDao.insertPemasukan(pemasukan)
            loadAllPemasukan()
            updateTotalPemasukan()
        }
    }

    fun updatePemasukan(pemasukan: Pemasukan) {
        viewModelScope.launch {
            pemasukanDao.updatePemasukan(pemasukan)
            loadAllPemasukan()
            updateTotalPemasukan()
        }
    }

    fun updateTotalPemasukan() {
        viewModelScope.launch {
            _totalPemasukan.value = pemasukanDao.getTotalPemasukan()
        }
    }

    fun deletePemasukan(pemasukan: Pemasukan) {
        viewModelScope.launch {
            pemasukanDao.deletePemasukan(pemasukan)
            loadAllPemasukan()
            updateTotalPemasukan()
        }
    }

    fun searchPemasukan(query: String) {
        viewModelScope.launch {
            _allPemasukan.value = pemasukanDao.searchPemasukan(query)
        }
    }
}
