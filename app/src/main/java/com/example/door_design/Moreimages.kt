package com.example.door_design

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.door_design.databinding.ActivityMoreimagesBinding

class Moreimages : AppCompatActivity() {
    private lateinit var binding: ActivityMoreimagesBinding

    val itemlist= mutableListOf<Model_moreimages>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMoreimagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val itemName = sharedPref.getString("itemName", "")
        binding.tvName.text = itemName





        binding.recyclerview.layoutManager = GridLayoutManager(this, 2)

        when (itemName) {
            "Double Door" -> doubledoor()
            "Flush Door" -> Flushdoor()
            "French Door" -> Frenchdoor()
            "Glass Door" -> Glassdoor()
            "Panel Door" -> Paneldoor()
            "Pocket Door" -> Pocketdoor()

            else -> {
                // Handle the case when the category is not recognized
            }
        }

        binding.recyclerview.adapter = Adapter_moreimages(itemlist, this)
    }

    fun back(view: View) {
        finish()
    }
    fun doubledoor(){

        itemlist.add(Model_moreimages(R.drawable.doubleone))
        itemlist.add(Model_moreimages(R.drawable.doubletwo))
        itemlist.add(Model_moreimages(R.drawable.doublethree))
        itemlist.add(Model_moreimages(R.drawable.doublefour))
        itemlist.add(Model_moreimages(R.drawable.doublefive))
        itemlist.add(Model_moreimages(R.drawable.doublesix))
        itemlist.add(Model_moreimages(R.drawable.doubleseven))
        itemlist.add(Model_moreimages(R.drawable.doubleeight))
        itemlist.add(Model_moreimages(R.drawable.doublenine))
        itemlist.add(Model_moreimages(R.drawable.doubleten))
        itemlist.add(Model_moreimages(R.drawable.doubleeleven))
        itemlist.add(Model_moreimages(R.drawable.doubletwelve))
        itemlist.add(Model_moreimages(R.drawable.doublethirteen))
        itemlist.add(Model_moreimages(R.drawable.doublefourteen))
        itemlist.add(Model_moreimages(R.drawable.doublefifteen))
        itemlist.add(Model_moreimages(R.drawable.doublesixteen))
        itemlist.add(Model_moreimages(R.drawable.doubleseventeen))
        itemlist.add(Model_moreimages(R.drawable.doubleeighteen))
    }
    fun Flushdoor(){

        itemlist.add(Model_moreimages(R.drawable.flushdoorone))
        itemlist.add(Model_moreimages(R.drawable.flushdoortwo))
        itemlist.add(Model_moreimages(R.drawable.flushdoorthree))
        itemlist.add(Model_moreimages(R.drawable.flushdoorfour))
        itemlist.add(Model_moreimages(R.drawable.flushdoorfive))
        itemlist.add(Model_moreimages(R.drawable.flushdoorseven))
        itemlist.add(Model_moreimages(R.drawable.flushdooreight))
        itemlist.add(Model_moreimages(R.drawable.flushdoornine))
        itemlist.add(Model_moreimages(R.drawable.flushdoorten))

    }
    fun Frenchdoor(){

        itemlist.add(Model_moreimages(R.drawable.frenchone))
        itemlist.add(Model_moreimages(R.drawable.frenchtwo))
        itemlist.add(Model_moreimages(R.drawable.frenchthree))
        itemlist.add(Model_moreimages(R.drawable.frenchfour))
        itemlist.add(Model_moreimages(R.drawable.frenchfive))
        itemlist.add(Model_moreimages(R.drawable.frenchsix))
        itemlist.add(Model_moreimages(R.drawable.frenchseven))
        itemlist.add(Model_moreimages(R.drawable.frencheight))
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
        itemlist.add(Model_moreimages(R.drawable.frenchtwenty))
        itemlist.add(Model_moreimages(R.drawable.frenchtwentyone))
        itemlist.add(Model_moreimages(R.drawable.frenchtwentytwo))
        itemlist.add(Model_moreimages(R.drawable.frenchtwentythree))
    }
    fun Glassdoor(){

        itemlist.add(Model_moreimages(R.drawable.glassone))
        itemlist.add(Model_moreimages(R.drawable.glasstwo))
        itemlist.add(Model_moreimages(R.drawable.glassthree))
        itemlist.add(Model_moreimages(R.drawable.glassfour))
        itemlist.add(Model_moreimages(R.drawable.glassfive))
        itemlist.add(Model_moreimages(R.drawable.glasssix))
        itemlist.add(Model_moreimages(R.drawable.glassseven))
        itemlist.add(Model_moreimages(R.drawable.glasseight))
        itemlist.add(Model_moreimages(R.drawable.glassnine))
        itemlist.add(Model_moreimages(R.drawable.glassten))
        itemlist.add(Model_moreimages(R.drawable.glasseleven))
        itemlist.add(Model_moreimages(R.drawable.glasstwelve))
        itemlist.add(Model_moreimages(R.drawable.glassthirteen))
        itemlist.add(Model_moreimages(R.drawable.glassfourteen))
        itemlist.add(Model_moreimages(R.drawable.glassfifteen))
        itemlist.add(Model_moreimages(R.drawable.glasssixteen))
        itemlist.add(Model_moreimages(R.drawable.glassseventeen))
        itemlist.add(Model_moreimages(R.drawable.glasseighteen))
        itemlist.add(Model_moreimages(R.drawable.glassninteen))
        itemlist.add(Model_moreimages(R.drawable.glasstwenty))

    }
    fun Paneldoor(){

        itemlist.add(Model_moreimages(R.drawable.panelone))
        itemlist.add(Model_moreimages(R.drawable.paneltwo))
        itemlist.add(Model_moreimages(R.drawable.panelthree))
        itemlist.add(Model_moreimages(R.drawable.panelfour))
        itemlist.add(Model_moreimages(R.drawable.panelfive))
        itemlist.add(Model_moreimages(R.drawable.panelsix))
        itemlist.add(Model_moreimages(R.drawable.panelseveen))
        itemlist.add(Model_moreimages(R.drawable.paneleight))
        itemlist.add(Model_moreimages(R.drawable.panelnine))
        itemlist.add(Model_moreimages(R.drawable.panelten))
        itemlist.add(Model_moreimages(R.drawable.paneleleven))
        itemlist.add(Model_moreimages(R.drawable.paneltwelve))
        itemlist.add(Model_moreimages(R.drawable.panelthirteen))
        itemlist.add(Model_moreimages(R.drawable.panelfoureteen))
        itemlist.add(Model_moreimages(R.drawable.panelfifteen))
        itemlist.add(Model_moreimages(R.drawable.panelsixteen))
        itemlist.add(Model_moreimages(R.drawable.panelseventeen))
        itemlist.add(Model_moreimages(R.drawable.paneleighteen))
        itemlist.add(Model_moreimages(R.drawable.panelninteen))

    }
    fun Pocketdoor(){

        itemlist.add(Model_moreimages(R.drawable.pocketone))
        itemlist.add(Model_moreimages(R.drawable.pockettwo))
        itemlist.add(Model_moreimages(R.drawable.pocketthree))
        itemlist.add(Model_moreimages(R.drawable.pocketfour))
        itemlist.add(Model_moreimages(R.drawable.pocketfive))
        itemlist.add(Model_moreimages(R.drawable.pocketsix))
        itemlist.add(Model_moreimages(R.drawable.pocketseven))
        itemlist.add(Model_moreimages(R.drawable.pocketeight))
        itemlist.add(Model_moreimages(R.drawable.pocketnine))
        itemlist.add(Model_moreimages(R.drawable.pocketten))
        itemlist.add(Model_moreimages(R.drawable.pocketeleven))
        itemlist.add(Model_moreimages(R.drawable.pockettwelve))
        itemlist.add(Model_moreimages(R.drawable.pocketthirteen))
        itemlist.add(Model_moreimages(R.drawable.pocketfourteen))
        itemlist.add(Model_moreimages(R.drawable.pocketfifteen))
        itemlist.add(Model_moreimages(R.drawable.pocketsixteen))
        itemlist.add(Model_moreimages(R.drawable.pocketseventeen))

    }
}