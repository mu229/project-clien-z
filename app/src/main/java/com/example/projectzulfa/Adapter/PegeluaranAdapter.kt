package com.example.projectzulfa.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectzulfa.entitas.Pengeluaran
import com.example.uas.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class PengeluaranAdapter(
    private var pengeluaranList: List<Pengeluaran>,
    private val onEditClick: (Pengeluaran) -> Unit,
    private val onDeleteClick: (Pengeluaran) -> Unit
) : RecyclerView.Adapter<PengeluaranAdapter.PengeluaranViewHolder>() {

    class PengeluaranViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewNama: TextView = view.findViewById(R.id.textViewNama)
        val textViewNominal: TextView = view.findViewById(R.id.textViewNominal)
        val textViewTanggal: TextView = view.findViewById(R.id.textViewTanggal)
        val buttonEdit: View = view.findViewById(R.id.buttonEdit)
        val buttonDelete: View = view.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengeluaranViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pengeluaran, parent, false)
        return PengeluaranViewHolder(view)
    }

    override fun onBindViewHolder(holder: PengeluaranViewHolder, position: Int) {
        val pengeluaran = pengeluaranList[position]
        holder.textViewNama.text = pengeluaran.nama
        holder.textViewNominal.text = NumberFormat.getNumberInstance(Locale("id", "ID")).format(pengeluaran.nominal)
        holder.textViewTanggal.text = SimpleDateFormat("dd/MM/yyyy", Locale("id", "ID")).format(Date(pengeluaran.tanggal))

        holder.buttonEdit.setOnClickListener { onEditClick(pengeluaran) }
        holder.buttonDelete.setOnClickListener { onDeleteClick(pengeluaran) }
    }

    override fun getItemCount() = pengeluaranList.size

    fun updateList(newList: List<Pengeluaran>) {
        pengeluaranList = newList
        notifyDataSetChanged()
    }
}