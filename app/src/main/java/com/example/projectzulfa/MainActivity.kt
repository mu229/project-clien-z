package com.example.projectzulfa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.uas.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iconTabungan = findViewById<ImageView>(R.id.icon_tabugan)
        val iconPengeluaran = findViewById<ImageView>(R.id.icon_pegeluaran)

        iconTabungan.setOnClickListener {
            val intent = Intent(this, TabunganActivity::class.java)
            startActivity(intent)
        }

        iconPengeluaran.setOnClickListener {
            val intent = Intent(this, TentangKita::class.java)
            startActivity(intent)
        }



    }
}