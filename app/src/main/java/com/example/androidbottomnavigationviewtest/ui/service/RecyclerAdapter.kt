package com.example.androidbottomnavigationviewtest.ui.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbottomnavigationviewtest.R
import com.example.androidbottomnavigationviewtest.ui.model.Task

class RecyclerAdapter(
    private val tasks: MutableList<Task>,
) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        val taskTV = itemView.findViewById<TextView>(R.id.taskTV)
        val flagRB = itemView.findViewById<RadioButton>(R.id.radioRB)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskTV.text = task.task
        holder.flagRB.isChecked = task.flag
    }
}