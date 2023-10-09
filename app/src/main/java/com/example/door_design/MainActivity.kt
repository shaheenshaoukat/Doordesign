package com.example.door_design

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.door_design.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var timeout = 1000
    var topanim: Animation? = null
    var bottomanim: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        topanim = AnimationUtils.loadAnimation(this, R.anim.topanim)
        bottomanim = AnimationUtils.loadAnimation(this, R.anim.bottomanim)
        binding.animationView.setAnimation(topanim)
        binding.text.setAnimation(bottomanim)
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, Home::class.java) // Replace MainActivity with your desired activity.
            startActivity(intent)
            finish()
        }, timeout.toLong())

    }
}