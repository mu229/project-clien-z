package com.example.projectzulfa

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectzulfa.Adapter.NoteAdapter
import com.example.projectzulfa.Database.AppDatabase3
import com.example.projectzulfa.entitas.Note
import com.example.uas.R
import com.example.uas.databinding.FragmentNoteBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var database: AppDatabase3

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase3.getInstance(requireContext())
        noteAdapter = NoteAdapter(emptyList()) { note -> deleteNote(note) }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchNotes(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.addButton.setOnClickListener {
            showAddNoteDialog()
        }

        loadNotes()
    }

    private fun loadNotes() {
        lifecycleScope.launch(Dispatchers.IO) {
            val notes = database.noteDao().getAllNotes()
            withContext(Dispatchers.Main) {
                noteAdapter.updateNotes(notes)
            }
        }
    }

    private fun searchNotes(query: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val notes = database.noteDao().searchNotes("%$query%")
            withContext(Dispatchers.Main) {
                noteAdapter.updateNotes(notes)
            }
        }
    }

    private fun showAddNoteDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_note, null)
        val titleEditText = dialogView.findViewById<TextInputEditText>(R.id.titleEditText)
        val contentEditText = dialogView.findViewById<TextInputEditText>(R.id.contentEditText)
        val buttonTambah = dialogView.findViewById<Button>(R.id.buttonTambah)
        val buttonBatal = dialogView.findViewById<Button>(R.id.buttonBatal)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        buttonTambah.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()
            if (title.isNotEmpty() && content.isNotEmpty()) {
                addNote(Note(title = title, content = content))
                dialog.dismiss()
            } else {
                if (title.isEmpty()) {
                    titleEditText.error = "Judul tidak boleh kosong"
                }
                if (content.isEmpty()) {
                    contentEditText.error = "Isi catatan tidak boleh kosong"
                }
            }
        }

        buttonBatal.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun addNote(note: Note) {
        lifecycleScope.launch(Dispatchers.IO) {
            database.noteDao().insertNote(note)
            loadNotes()
        }
    }

    private fun deleteNote(note: Note) {
        lifecycleScope.launch(Dispatchers.IO) {
            database.noteDao().deleteNote(note)
            loadNotes()
        }
    }
}