package com.example.door_design

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val itemlist: List<Model>, private val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentitem = itemlist[position]
        holder.img.setImageResource(currentitem.image)
        holder.name.text = currentitem.text


        holder.itemView.setOnClickListener() {
            val intent = Intent(context, Moreimages::class.java)
            val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("itemName", currentitem.text)
            editor.apply()
            context.startActivity(intent)

        }


    }
    override fun getItemCount(): Int {
        return itemlist.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)
        val name: TextView = itemView.findViewById(R.id.name)
    }
}