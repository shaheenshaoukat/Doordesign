@file:Suppress("DEPRECATION")

package com.example.door_design

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.door_design.databinding.ActivityHomeBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    var list: ArrayList<FavImageModel>? = null
    var myDb: DatabaseHelper? = null

    var adapter: FavImageAdapter? = null
    var modelclass: FavImageModel? = null
    val itemlist= mutableListOf<Model_moreimages>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val carousel: ImageCarousel = findViewById<ImageCarousel>(R.id.carousel)
        //carouselcode start
        carousel.registerLifecycle(lifecycle)
        val list: MutableList<CarouselItem> = ArrayList()
        list.add(CarouselItem(R.drawable.one, "hello"))
        list.add(CarouselItem(R.drawable.two, "hello"))
        list.add(CarouselItem(R.drawable.three, "hello"))
        list.add(CarouselItem(R.drawable.five, "hello"))
        list.add(CarouselItem(R.drawable.six, "hello"))
        list.add(CarouselItem(R.drawable.seven, "hello"))
        list.add(CarouselItem(R.drawable.eight, "hello"))
        list.add(CarouselItem(R.drawable.nine, "hello"))
        list.add(CarouselItem(R.drawable.ten, "hello"))
        carousel.autoPlay = true
        carousel.showCaption = false
        carousel.autoPlayDelay = 2000
        carousel.setData(list)
        //carouselcode end
        categoryrecycler()
    }

    fun category(view: View) {
        binding.favouriterecycler.visibility= View.GONE
        binding.trendingrecycler.visibility= View.GONE
        binding.categoryrecycler.visibility= View.VISIBLE

        binding.category.setBackgroundResource(R.color.mycll)
        binding.favourite.setBackgroundResource(R.color.white)
        binding.trending.setBackgroundResource(R.color.white)

        binding.category.setTextColor(ContextCompat.getColor(this, R.color.white))
        binding.favourite.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.trending.setTextColor(ContextCompat.getColor(this, R.color.black))


    }
    fun favourite(view: View) {
        binding.favouriterecycler.visibility= View.VISIBLE
        binding.trendingrecycler.visibility= View.GONE
        binding.categoryrecycler.visibility= View.GONE

        binding.category.setBackgroundResource(R.color.white)
        binding.favourite.setBackgroundResource(R.color.mycll)
        binding.trending.setBackgroundResource(R.color.white)

        binding.category.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.favourite.setTextColor(ContextCompat.getColor(this, R.color.white))
        binding.trending.setTextColor(ContextCompat.getColor(this, R.color.black))
        favourite()



    }
    fun trending(view: View) {
        binding.favouriterecycler.visibility= View.GONE
        binding.trendingrecycler.visibility= View.VISIBLE
        binding.categoryrecycler.visibility= View.GONE

        binding.category.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.favourite.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.trending.setTextColor(ContextCompat.getColor(this, R.color.white))


        binding.category.setBackgroundResource(R.color.white)
        binding.favourite.setBackgroundResource(R.color.white)
        binding.trending.setBackgroundResource(R.color.mycll)
        trendingrecycler()





    }
    fun categoryrecycler(){
        binding.categoryrecycler.layoutManager = GridLayoutManager(this, 2)
        val itemlist= mutableListOf<Model>()

        itemlist.add(Model("Double Door",R.drawable.doubleeight))
        itemlist.add(Model("Flush Door",R.drawable.flushdoorfour))
        itemlist.add(Model("French Door",R.drawable.frenchtwenty))
        itemlist.add(Model("Glass Door",R.drawable.glasseight))
        itemlist.add(Model("Panel Door",R.drawable.paneleight))
        itemlist.add(Model("Pocket Door",R.drawable.pocketseventeen))


        binding.categoryrecycler.adapter= CategoryAdapter(itemlist,this)



    }
    override fun onBackPressed() {
        super.onBackPressed()
        // Create an AlertDialog for confirmation
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Exit App")
        alertDialogBuilder.setMessage("Are you sure you want to exit the app?")

        // Add "Yes" button
        alertDialogBuilder.setPositiveButton("Yes") { dialog, which ->
            // Finish the activity and exit the app
            finishAffinity()        }

        // Add "No" button
        alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            // Dismiss the dialog and continue with the app
            dialog.dismiss()
        }

        // Show the dialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    fun favourite(){
        /*ads code end*/list = java.util.ArrayList()

        binding.favouriterecycler.layoutManager = LinearLayoutManager(this)

        myDb = DatabaseHelper(this)

        myDb!!.writableDatabase


        val cursor = myDb!!.getAllData()



        if (cursor.count == 0) {
            Toast.makeText(this, "data not found", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, Home::class.java)
            startActivity(intent)
            return
        }

        val buffer = StringBuffer()

        while (cursor.moveToNext()) {
            modelclass = FavImageModel(
                cursor.getString(0),
                cursor.getString(1)
            )
            list!!.add(modelclass!!)
            adapter = FavImageAdapter(list!!, myDb!!, this)
            binding.favouriterecycler.adapter = adapter
        }

    }
    fun trendingrecycler(){
        binding.trendingrecycler.layoutManager = GridLayoutManager(this, 2)
        itemlist.add(Model_moreimages(R.drawable.doubleeight))
        itemlist.add(Model_moreimages(R.drawable.doublenine))
        itemlist.add(Model_moreimages(R.drawable.doubleten))
        itemlist.add(Model_moreimages(R.drawable.doubleeleven))
        itemlist.add(Model_moreimages(R.drawable.doubletwelve))
        itemlist.add(Model_moreimages(R.drawable.doublethirteen))
        itemlist.add(Model_moreimages(R.drawable.frenchseven))
        itemlist.add(Model_moreimages(R.drawable.frenchtwenty))
        itemlist.add(Model_moreimages(R.drawable.frenchnine))
        itemlist.add(Model_moreimages(R.drawable.frenchten))
        itemlist.add(Model_moreimages(R.drawable.frencheleven))
        itemlist.add(Model_moreimages(R.drawable.frenchtwelve))
        itemlist.add(Model_moreimages(R.drawable.frenchthirteen))
        itemlist.add(Model_moreimages(R.drawable.frenchfourteen))
        itemlist.add(Model_moreimages(R.drawable.frenchfifteen))
        itemlist.add(Model_moreimages(R.drawable.frenchsixteen))
        itemlist.add(Model_moreimages(R.drawable.frenchseventeen))
        itemlist.add(Model_moreimages(R.drawable.frencheighteen))
        itemlist.add(Model_moreimages(R.drawable.frenchninteen))
        itemlist.add(Model_moreimages(R.drawable.glassten))
        itemlist.add(Model_moreimages(R.drawable.glasseleven))
        itemlist.add(Model_moreimages(R.drawable.glasstwelve))
        itemlist.add(Model_moreimages(R.drawable.glassthirteen))
        itemlist.add(Model_moreimages(R.drawable.glassfourteen))
        itemlist.add(Model_moreimages(R.drawable.panelten))
        itemlist.add(Model_moreimages(R.drawable.paneleleven))
        itemlist.add(Model_moreimages(R.drawable.paneltwelve))
        itemlist.add(Model_moreimages(R.drawable.panelthirteen))
        itemlist.add(Model_moreimages(R.drawable.panelfoureteen))
        itemlist.add(Model_moreimages(R.drawable.panelfifteen))
        itemlist.add(Model_moreimages(R.drawable.pocketfive))
        itemlist.add(Model_moreimages(R.drawable.pocketsix))
        itemlist.add(Model_moreimages(R.drawable.pocketseven))
        itemlist.add(Model_moreimages(R.drawable.pocketeight))
        itemlist.add(Model_moreimages(R.drawable.pocketnine))
        itemlist.add(Model_moreimages(R.drawable.pocketten))
        binding.trendingrecycler.adapter = Adapter_moreimages(itemlist, this)

    }
}