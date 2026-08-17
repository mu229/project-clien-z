package com.example.projectzulfa

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectzulfa.Adapter.PengeluaranAdapter
import com.example.projectzulfa.Database.AppDatabase2
import com.example.projectzulfa.entitas.Pengeluaran
import com.example.projectzulfa.model.PengeluaranViewFacrory
import com.example.projectzulfa.model.PengeluaranViewModel
import com.example.uas.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class PengeluaranFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PengeluaranAdapter
    private lateinit var buttonTambah: Button
    private lateinit var textViewTotal: TextView
    private lateinit var database: AppDatabase2
    private lateinit var pengeluaranViewModel: PengeluaranViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pengeluaran, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewPengeluaran)
        buttonTambah = view.findViewById(R.id.buttonTambah)
        textViewTotal = view.findViewById(R.id.textViewTotal)

        database = AppDatabase2 .getDatabase(requireContext())

        val pengeluaranDao = database.pengeluaranDao()
        val factory = PengeluaranViewFacrory(pengeluaranDao)
        pengeluaranViewModel = ViewModelProvider(this, factory).get(PengeluaranViewModel::class.java)

        setupRecyclerView()
        setupTambahButton()
        observePengeluaran()

        return view
    }

    private fun setupRecyclerView() {
        adapter = PengeluaranAdapter(
            emptyList(),
            onEditClick = { pengeluaran -> editPengeluaran(pengeluaran) },
            onDeleteClick = { pengeluaran -> hapusPengeluaran(pengeluaran) }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }



    private fun setupTambahButton() {
        buttonTambah.setOnClickListener {
            tambahPengeluaran()
        }
    }

    private fun observePengeluaran() {
        pengeluaranViewModel.allPengeluaran.observe(viewLifecycleOwner) { pengeluaran ->
            adapter.updateList(pengeluaran)
        }
        pengeluaranViewModel.totalPengeluaran.observe(viewLifecycleOwner) { total ->
            textViewTotal.text = total?.let { NumberFormat.getNumberInstance(Locale("id", "ID")).format(it) } ?: "0"
        }
    }

    private fun tambahPengeluaran() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_tambah_pengeluaran, null)
        val editTextNama = dialogView.findViewById<TextInputEditText>(R.id.editTextNama)
        val editTextNominal = dialogView.findViewById<TextInputEditText>(R.id.editTextNominal)
        val buttonTambah = dialogView.findViewById<Button>(R.id.buttonTambah)
        val buttonBatal = dialogView.findViewById<Button>(R.id.buttonBatal)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        buttonTambah.setOnClickListener {
            val nama = editTextNama.text.toString()
            val nominal = editTextNominal.text.toString().toLongOrNull() ?: 0
            if (nama.isNotBlank() && nominal > 0) {
                val newPengeluaran = Pengeluaran(nama = nama, nominal = nominal)
                lifecycleScope.launch {
                    pengeluaranViewModel.insertPengeluaran(newPengeluaran)
                }
                dialog.dismiss()
            } else {
                if (nama.isBlank()) {
                    editTextNama.error = "Nama pengeluaran tidak boleh kosong"
                }
                if (nominal <= 0) {
                    editTextNominal.error = "Nominal harus lebih besar dari 0"
                }
            }
        }

        buttonBatal.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun editPengeluaran(pengeluaran: Pengeluaran) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_pengeluaran, null)
        val editTextNama = dialogView.findViewById<EditText>(R.id.editTextNama)
        val editTextNominal = dialogView.findViewById<EditText>(R.id.editTextNominal)

        editTextNama.setText(pengeluaran.nama)
        editTextNominal.setText(pengeluaran.nominal.toString())

        AlertDialog.Builder(requireContext())
            .setTitle("Edit Pengeluaran")
            .setView(dialogView)
            .setPositiveButton("Simpan") { _, _ ->
                val nama = editTextNama.text.toString()
                val nominal = editTextNominal.text.toString().toLongOrNull() ?: 0
                val updatedPengeluaran = pengeluaran.copy(nama = nama, nominal = nominal)
                lifecycleScope.launch {
                    pengeluaranViewModel.updatePengeluaran(updatedPengeluaran)
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun hapusPengeluaran(pengeluaran: Pengeluaran) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Pengeluaran")
            .setMessage("Apakah Anda yakin ingin menghapus pengeluaran ini?")
            .setPositiveButton("Ya") { _, _ ->
                lifecycleScope.launch {
                    pengeluaranViewModel.deletePengeluaran(pengeluaran)
                }
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


}