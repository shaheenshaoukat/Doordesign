package com.example.door_design

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class Adapter_moreimages (private val itemlist: List<Model_moreimages>, private val context: Context) :
    RecyclerView.Adapter<Adapter_moreimages.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.more_images_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentitem = itemlist[position]
        holder.img.setImageResource(currentitem.image)



        holder.itemView.setOnClickListener() {
            val intent = Intent(context, Details_Activity::class.java)
            intent.putExtra("images", currentitem.image)
            intent.putExtra("position", position) // Pass the clicked position
            intent.putIntegerArrayListExtra("imageList", getImageList())
            context.startActivity(intent)


        }


    }
    override fun getItemCount(): Int {
        return itemlist.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imges)
    }
    private fun getImageList(): ArrayList<Int> {
        val imageList = ArrayList<Int>()
        for (item in itemlist) {
            imageList.add(item.image)
        }
        return imageList
    }
}