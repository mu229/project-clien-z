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
import com.example.projectzulfa.Adapter.PemasukanAdapter
import com.example.projectzulfa.Database.AppDatabase

import com.example.projectzulfa.model.Pemasukan
import com.example.projectzulfa.model.PemasukanViewModel
import com.example.projectzulfa.model.PemasukanViewModelFactory
import com.example.uas.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class PemasukanFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PemasukanAdapter
    private lateinit var editTextSearch: EditText
    private lateinit var buttonTambah: Button
    private lateinit var textViewTotal: TextView
    private lateinit var database: AppDatabase
    private lateinit var pemasukanViewModel: PemasukanViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pemasukan, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewPemasukan)
        editTextSearch = view.findViewById(R.id.editTextSearch)
        buttonTambah = view.findViewById(R.id.buttonTambah)
        textViewTotal = view.findViewById(R.id.textViewTotal)

        database = AppDatabase.getDatabase(requireContext())

        val pemasukanDao = database.pemasukanDao()
        val factory = PemasukanViewModelFactory(pemasukanDao)
        pemasukanViewModel = ViewModelProvider(this, factory).get(PemasukanViewModel::class.java)

        setupRecyclerView()
        setupSearchFunction()
        setupTambahButton()
        observePemasukan()

        return view
    }

    private fun setupRecyclerView() {
        adapter = PemasukanAdapter(
            emptyList(),
            onEditClick = { pemasukan -> editPemasukan(pemasukan) },
            onDeleteClick = { pemasukan -> hapusPemasukan(pemasukan) }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupSearchFunction() {
        editTextSearch.addTextChangedListener { text ->
            pemasukanViewModel.searchPemasukan("%${text.toString()}%")
        }
    }

    private fun setupTambahButton() {
        buttonTambah.setOnClickListener {
            tambahPemasukan()
        }
    }

    private fun observePemasukan() {
        pemasukanViewModel.allPemasukan.observe(viewLifecycleOwner) { pemasukan ->
            adapter.updateList(pemasukan)
        }
        pemasukanViewModel.totalPemasukan.observe(viewLifecycleOwner) { total ->
            textViewTotal.text = total?.let { NumberFormat.getNumberInstance(Locale("id", "ID")).format(it) } ?: "0"
        }
    }

    private fun tambahPemasukan() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_tambah_pemasukan, null)
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
            val newPemasukan = Pemasukan(nama = nama, nominal = nominal)
            lifecycleScope.launch {
                pemasukanViewModel.insertPemasukan(newPemasukan)
            }
            dialog.dismiss()
        }

        buttonBatal.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun editPemasukan(pemasukan: Pemasukan) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_pemasukan, null)
        val editTextNama = dialogView.findViewById<EditText>(R.id.editTextNama)
        val editTextNominal = dialogView.findViewById<EditText>(R.id.editTextNominal)

        editTextNama.setText(pemasukan.nama)
        editTextNominal.setText(pemasukan.nominal.toString())

        AlertDialog.Builder(requireContext())
            .setTitle("Edit Pemasukan")
            .setView(dialogView)
            .setPositiveButton("Simpan") { _, _ ->
                val nama = editTextNama.text.toString()
                val nominal = editTextNominal.text.toString().toLongOrNull() ?: 0
                val updatedPemasukan = pemasukan.copy(nama = nama, nominal = nominal)
                lifecycleScope.launch {
                    pemasukanViewModel.updatePemasukan(updatedPemasukan)
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun hapusPemasukan(pemasukan: Pemasukan) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Pemasukan")
            .setMessage("Apakah Anda yakin ingin menghapus pemasukan ini?")
            .setPositiveButton("Ya") { _, _ ->
                lifecycleScope.launch {
                    pemasukanViewModel.deletePemasukan(pemasukan)
                }
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_pemasukan, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
