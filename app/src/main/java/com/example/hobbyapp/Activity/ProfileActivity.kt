package com.example.hobbyapp.Activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.ActivityProfileBinding
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import java.io.File

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Навигация
        binding.exitImg.setOnClickListener {
            startActivity(Intent(this, IntroActivity::class.java))
        }
        binding.kelImg.setOnClickListener {
            startActivity(Intent(this, TermsActivity::class.java))
        }
        binding.qupImg.setOnClickListener {
            startActivity(Intent(this, PrivacyActivity::class.java))
        }
        binding.qolImg.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        binding.bottomNavigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_edit -> {
                    startActivity(Intent(this, PostActivity::class.java))
                    true
                }
                R.id.nav_chat -> {
                    startActivity(Intent(this, ChatActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    true
                }
                R.id.nav_friends -> {
                    startActivity(Intent(this, FriendsActivity::class.java))
                    true
                }
                else -> false
            }
        }


        // Редактировать профиль
        binding.profileImgProf.setOnClickListener {
            startActivity(Intent(this, ProfileEditActivity::class.java))
        }

        // Контакты
        binding.contImg.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Байланыс")
                .setMessage("Разработчик: example@gmail.com\nTelegram: @developer")
                .setPositiveButton("ОК", null)
                .show()
        }

        // Загрузка профиля
        loadProfileData()
    }

    private fun loadProfileData() {
        val sharedPref = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val name = sharedPref.getString("name", "Example")
        val imagePath = sharedPref.getString("image_path", null)

        binding.nameProfTxt.text = name

        if (!imagePath.isNullOrEmpty()) {
            val file = File(imagePath)
            if (file.exists()) {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                binding.profileImgProf.setImageBitmap(bitmap)
            } else {
                Log.e("ProfileActivity", "Файл изображения не найден: $imagePath")
            }
        }
    }
}
