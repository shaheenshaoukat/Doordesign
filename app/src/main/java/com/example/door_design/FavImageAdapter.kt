package com.example.door_design

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.door_design.databinding.ItemListBinding

class FavImageAdapter(
    private val mylist: ArrayList<FavImageModel>,
    private val myDb: DatabaseHelper,
    private val context: Context
) : RecyclerView.Adapter<FavImageAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = mylist[position]

        holder.binding.rcImage.setImageResource(currentItem.name.toInt())

        holder.itemView.setOnClickListener {
            val intent = Intent(context, Home::class.java)
            intent.putExtra("image", currentItem.name)
            intent.putExtra("name", "Favourite")
            context.startActivity(intent)
            (context as Activity).finish()
        }

        holder.binding.button.setOnClickListener {
            val deletedRow = myDb.deleteData(currentItem.id)
            if (deletedRow > 0) {
                Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, Home::class.java)
                context.startActivity(intent)
                (context as Activity).finish()
            } else {
                Toast.makeText(context, "Data not Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return mylist.size
    }
}