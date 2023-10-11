package com.example.door_design

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.door_design.databinding.ActivityDetailsBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Details_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    var myDb: DatabaseHelper? = null
    private var isFavorite = false
    private val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val itemName = sharedPref.getString("itemName", "")
        binding.tvname.text = itemName

        myDb = DatabaseHelper(this@Details_Activity)
        myDb!!.writableDatabase


        // Get the list of image resource IDs and the clicked position from the intent
        val imageList = intent.getIntegerArrayListExtra("imageList") ?: emptyList()
        val position = intent.getIntExtra("position", 0)

        // Rearrange the list so that the clicked image is displayed first
        val rearrangedList = ArrayList<Int>(imageList.size)
        rearrangedList.add(imageList[position])
        for (i in 0 until imageList.size) {
            if (i != position) {
                rearrangedList.add(imageList[i])
            }
        }

        // Create an adapter for the ViewPager2
        val adapter = ImagePagerAdapter(rearrangedList)
        binding.viewPager.adapter = adapter


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val imageToInsert = rearrangedList[position]

                binding.favourite.setOnClickListener {

                    isFavorite = !isFavorite
                    if (isFavorite) {
                        binding.favourite.setImageResource(R.drawable.baseline_favorite_24)
                        binding.favourite.isEnabled = false // Disable the button temporarily
                        val IsInserted = myDb!!.insertData(imageToInsert)
                        if (IsInserted) {
                            Toast.makeText(
                                this@Details_Activity,
                                "Added to favorites",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            val intent = Intent(this@Details_Activity, Home::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@Details_Activity,
                                "Data not inserted",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    } else {
                        binding.favourite.setImageResource(R.drawable.baseline_favorite_border_24)
                    }
                }

                binding.share.setOnClickListener {
                    // Get the image to share based on the current position
                    val imageToShare = rearrangedList[position]

                    // Create a temporary file to store the image
                    val cachePath = File(applicationContext.cacheDir, "images")
                    cachePath.mkdirs()
                    val imageFile = File(cachePath, "shared_image.png")

                    // Save the image to the temporary file
                    try {
                        val fos = FileOutputStream(imageFile)
                        val imageBitmap = BitmapFactory.decodeResource(resources, imageToShare)
                        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                        fos.flush()
                        fos.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    // Create an intent to share the image
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "image/*"
                    val imageUri = FileProvider.getUriForFile(
                        this@Details_Activity,
                        applicationContext.packageName + ".provider",
                        imageFile
                    )
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)

                    // Launch the sharing dialog
                    startActivity(Intent.createChooser(shareIntent, "Share Image"))
                }
                binding.save.setOnClickListener {
                    if (checkPermission()) {
                        // Get the image to save based on the current position
                        val imageToSave = rearrangedList[position]

                        // Convert the resource ID to a Bitmap
                        val imageBitmap = BitmapFactory.decodeResource(resources, imageToSave)

                        // Create a ContentValues object to store the image details
                        val contentValues = ContentValues().apply {
                            put(MediaStore.Images.Media.DISPLAY_NAME, "my_image.jpg") // Specify the image file name
                            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg") // Specify the image MIME type
                        }

                        // Insert the image into the MediaStore
                        val imageUri = contentResolver.insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            contentValues
                        )
                        try {
                            val outputStream = contentResolver.openOutputStream(imageUri!!)
                            if (outputStream != null) {
                                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                                outputStream.close()
                                Toast.makeText(
                                    this@Details_Activity,
                                    "Image saved to gallery",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Toast.makeText(
                                this@Details_Activity,
                                "Failed to save image",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        requestPermission()
                    }
                }
            }
        })
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, proceed with image saving
                    // ...
                } else {
                    // Permission denied, show a message or handle it accordingly
                    Toast.makeText(
                        this,
                        "Permission denied. Image cannot be saved.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun back(view: View) {
        finish()
    }

    private fun getImageUriForPosition(position: Int): Uri {
        // Implement as needed
        return Uri.EMPTY
    }
}