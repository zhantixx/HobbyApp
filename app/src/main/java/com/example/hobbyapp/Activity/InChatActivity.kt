package com.example.hobbyapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hobbyapp.databinding.ActivityInChatBinding

class InChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получение имени пользователя
        val userName = intent.getStringExtra("userName")
        binding.textInChat.text = userName

        // Обработка нажатия на кнопку "Назад"
        binding.imgBackChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
            finish()  // Закрыть текущую активность
        }
    }
}
