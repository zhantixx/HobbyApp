package com.example.hobbyapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.hobbyapp.Activity.TermsActivity
import com.example.hobbyapp.Adapter.PostsAdapter
import com.example.hobbyapp.ViewModel.MainVIewModel
import com.example.hobbyapp.databinding.ActivityFriendProfileBinding

class FriendProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendProfileBinding
    private lateinit var viewModel: MainVIewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получаем данные из Intent
        val friendName = intent.getStringExtra("friendName") ?: "Аноним"
        val avatarResId = intent.getIntExtra("avatarResId", 0)

        // Назад
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, FriendsActivity::class.java))
        }

        // Установка данных друга
        binding.titleTtx1.text = friendName
        binding.specTxt1.text = "Студент"
        binding.addTxt1.text = "Астана"

        // Фото
        if (avatarResId != 0) {
            Glide.with(this)
                .load(avatarResId)
                .circleCrop()
                .into(binding.imgTop)
        }


        // ViewModel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MainVIewModel::class.java]

        // Посты конкретного друга
        viewModel.getPostsByAuthor(friendName).observe(this) { posts ->
            binding.friendPostsRecycler.layoutManager = LinearLayoutManager(this)
            binding.friendPostsRecycler.adapter = PostsAdapter(posts.toMutableList())
        }
    }
}
