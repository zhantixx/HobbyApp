package com.example.hobbyapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.ActivityAboutBinding
import com.example.hobbyapp.databinding.ActivityProfileBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn2.setOnClickListener {
            startActivity(Intent(this@AboutActivity, ProfileActivity::class.java))
        }


    }
}