package com.example.hobbyapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hobbyapp.Adapter.ChatAdapter
import com.example.hobbyapp.Domain.JetekshilerModel
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.ActivityChatBinding
import com.google.android.material.bottomnavigation.LabelVisibilityMode

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var adapter: ChatAdapter
    private val chatList = mutableListOf<JetekshilerModel>()  // Используем JetekshilerModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                R.id.nav_friends -> {
                    startActivity(Intent(this, FriendsActivity::class.java))
                    true
                }
                else -> false
            }
        }

        val userId = intent.getStringExtra("userId")
        val userName = intent.getStringExtra("userName")

        if (userId != null && userName != null) {
            val avatarResId = intent.getIntExtra("avatarResId", R.drawable.profile) // Укажи default

            val chatUser = JetekshilerModel(
                id = userId.toInt(),
                Name = userName,
                Category = "Category",
                Picture = avatarResId,
                Biography = "",
                Location = "",
                Mobile = "",
                schedule = null,
                Rating = 0.0,
                Experience = 0,
                Address = ""
            )

            chatList.add(0, chatUser)
        }

        adapter = ChatAdapter(chatList) { chatUser ->
            val intent = Intent(this@ChatActivity, InChatActivity::class.java)
            intent.putExtra("userId", chatUser.id.toString())
            intent.putExtra("userName", chatUser.Name)
            intent.putExtra("avatarUrl", chatUser.Picture)
            startActivity(intent)
        }

        binding.chatView.layoutManager = LinearLayoutManager(this)
        binding.chatView.adapter = adapter



    }
}
