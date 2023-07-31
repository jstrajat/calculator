package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdaptor(private val myList: List<ResultViewModel>): RecyclerView.Adapter<HistoryAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ResultViewModel = myList[position]
        holder.Equationtextbox.text = ResultViewModel.equation
        holder.SolutiontextBox.text = ResultViewModel.res
    }

    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val Equationtextbox: TextView = itemView.findViewById(R.id.Eq)
        val SolutiontextBox: TextView = itemView.findViewById(R.id.output)
    }
}

data class ResultViewModel(val equation : String, val res: String) {
}
