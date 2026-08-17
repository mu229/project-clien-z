package com.example.projectzulfa.Adapter

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectzulfa.model.Pemasukan
import com.example.uas.R
import java.util.Locale

class PemasukanAdapter(
    private var pemasukanList: List<Pemasukan>,
    private val onEditClick: (Pemasukan) -> Unit,
    private val onDeleteClick: (Pemasukan) -> Unit

) : RecyclerView.Adapter<PemasukanAdapter.PemasukanViewHolder>() {

    class PemasukanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewNama: TextView = view.findViewById(R.id.textViewNama)
        val textViewNominal: TextView = view.findViewById(R.id.textViewNominal)
        val buttonEdit: ImageButton = view.findViewById(R.id.buttonEdit)
        val buttonDelete: ImageButton = view.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemasukanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pemasukan, parent, false)
        return PemasukanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PemasukanViewHolder, position: Int) {
        val pemasukan = pemasukanList[position]
        holder.textViewNama.text = pemasukan.nama
        holder.textViewNominal.text = "Rp ${pemasukan.nominal.toNumberFormat()}"

        holder.buttonEdit.setOnClickListener { onEditClick(pemasukan) }
        holder.buttonDelete.setOnClickListener { onDeleteClick(pemasukan) }
    }

    override fun getItemCount() = pemasukanList.size

    fun updateList(newList: List<Pemasukan>) {
        pemasukanList = newList
        notifyDataSetChanged()
    }
}

// Extension function untuk memformat angka menjadi format uang
fun Long.toNumberFormat(): String {
    return NumberFormat.getNumberInstance(Locale("id", "ID")).format(this)
}