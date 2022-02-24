package com.guru.virtandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class StringAdapter(val list:List<String>):RecyclerView.Adapter<StringAdapter.Holder>() {
    class Holder(view: View):RecyclerView.ViewHolder(view) {
        val emailId: TextView = view.findViewById<TextView>(R.id.email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
      return  Holder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.email_item,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.emailId.text = list[position]
    }

    override fun getItemCount(): Int {
      return list.size
    }
}