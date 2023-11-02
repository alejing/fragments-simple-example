package com.example.simplenavigationfragments.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenavigationfragments.R
import com.example.simplenavigationfragments.database.user.Usuario


/**
 * Provide a reference to the type of views that you are using
 * (custom ViewHolder)
 */
class ItemAdapter(
    //private val navController: NavController,
    private val context: Context,
    private val dataset: List<Usuario>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewUser: TextView
        val textViewName: TextView
        val textViewLastName: TextView
        init {
            // Define click listener for the ViewHolder's View
            textViewUser = view.findViewById(R.id.item_user)
            textViewName = view.findViewById(R.id.item_name)
            textViewLastName = view.findViewById(R.id.item_lastName)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // Create a new view, which defines the UI of the list item
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = dataset[position]
        holder.textViewUser.text = context.resources.getString(R.string.prin_item_user, item.user)
        holder.textViewName.text = context.resources.getString(R.string.prin_item_name, item.name)
        holder.textViewLastName.text = context.resources.getString(R.string.prin_item_last_name, item.lastName)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
            //val action = PrincipalFragmentDirections.actionPrincipalFragmentToInicioFragment()
            //navController.navigate(action)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}