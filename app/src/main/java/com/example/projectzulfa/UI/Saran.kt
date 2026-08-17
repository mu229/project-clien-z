package com.example.projectzulfa.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.uas.R


import androidx.lifecycle.ViewModelProvider
import com.example.projectzulfa.model.OlahragaViewModel

class Saran : Fragment() {

    private lateinit var viewModel: OlahragaViewModel
    private lateinit var etSitUp: EditText
    private lateinit var etPullUp: EditText
    private lateinit var etBerlari: EditText
    private lateinit var btnHitung: Button
    private lateinit var tvHasil: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.saran, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(OlahragaViewModel::class.java)

        etSitUp = view.findViewById(R.id.etSitUp)
        etPullUp = view.findViewById(R.id.etPullUp)
        etBerlari = view.findViewById(R.id.etBerlari)
        btnHitung = view.findViewById(R.id.btnHitung)
        tvHasil = view.findViewById(R.id.tvHasil)

        btnHitung.setOnClickListener {
            hitungKalori()
        }

        // Memuat data yang tersimpan
        loadData()
    }

    private fun hitungKalori() {
        val sitUp = etSitUp.text.toString().toIntOrNull() ?: 0
        val pullUp = etPullUp.text.toString().toIntOrNull() ?: 0
        val berlari = etBerlari.text.toString().toIntOrNull() ?: 0

        val totalKalori = viewModel.hitungTotalKalori(sitUp, pullUp, berlari)

        tvHasil.text = "Total Kalori Terbakar: $totalKalori kalori"

        // Menyimpan data
        viewModel.simpanData(sitUp, pullUp, berlari)
    }

    private fun loadData() {
        val data = viewModel.muatData()
        etSitUp.setText(data.sitUp.toString())
        etPullUp.setText(data.pullUp.toString())
        etBerlari.setText(data.berlari.toString())
    }
}