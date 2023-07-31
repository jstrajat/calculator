package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryViewActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)


        val historyRecyclerView = findViewById<RecyclerView>(R.id.historyView)
        val inputview = findViewById<TextView>(R.id.Eq)
        val outputview = findViewById<TextView>(R.id.output)

        historyRecyclerView.layoutManager = LinearLayoutManager(this)


        val recyclerview = findViewById<RecyclerView>(R.id.historyView)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ResultViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ResultViewModel("$i" , "$i*i"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = HistoryAdaptor(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }
}