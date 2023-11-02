package com.example.simplenavigationfragments.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenavigationfragments.R
import com.example.simplenavigationfragments.database.user.Usuario

class ItemAdapter(
    private val context: Context,
    private val dataset: List<Usuario>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewUser: TextView = view.findViewById(R.id.item_user)
        val textViewName: TextView = view.findViewById(R.id.item_name)
        val textViewLastName: TextView = view.findViewById(R.id.item_lastName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textViewUser.text = context.resources.getString(R.string.prin_item_user, item.user)
        holder.textViewName.text = context.resources.getString(R.string.prin_item_name, item.name)
        holder.textViewLastName.text = context.resources.getString(R.string.prin_item_last_name, item.lastName)
    }

    override fun getItemCount() = dataset.size
}