package com.example.projectzulfa.model

import android.app.Application
import android.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel


class OlahragaViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    fun hitungTotalKalori(sitUp: Int, pullUp: Int, berlari: Int): Double {
        return (sitUp * 2.5) + (pullUp * 1.0) + (berlari * 4.5)
    }

    fun simpanData(sitUp: Int, pullUp: Int, berlari: Int) {
        sharedPreferences.edit().apply {
            putInt("sit_up", sitUp)
            putInt("pull_up", pullUp)
            putInt("berlari", berlari)
            apply()
        }
    }

    fun muatData(): OlahragaData {
        val sitUp = sharedPreferences.getInt("sit_up", 0)
        val pullUp = sharedPreferences.getInt("pull_up", 0)
        val berlari = sharedPreferences.getInt("berlari", 0)
        return OlahragaData(sitUp, pullUp, berlari)
    }
}

data class OlahragaData(val sitUp: Int, val pullUp: Int, val berlari: Int)