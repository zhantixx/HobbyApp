package com.example.hobbyapp.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hobbyapp.Adapter.FriendsAdapter
import com.example.hobbyapp.Adapter.PostsAdapter
import com.example.hobbyapp.R
import com.example.hobbyapp.ViewModel.MainVIewModel
import com.example.hobbyapp.databinding.ActivityFriendsBinding
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.snackbar.Snackbar

class FriendsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendsBinding
    private lateinit var viewModel: MainVIewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MainVIewModel::class.java]

        val prefs = getSharedPreferences("init_prefs", MODE_PRIVATE)
        val isFirstRun = prefs.getBoolean("firstRun", true)

        viewModel.insertSamplePosts()
        viewModel.insertSampleFriends()



        binding.friendsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewModel.friends.observe(this) { friends ->
            Log.d("FRIENDS_OBSERVE", "Найдено друзей: ${friends.size}")
            binding.friendsRecycler.adapter = FriendsAdapter(friends)
        }

        binding.postsRecycler.layoutManager = LinearLayoutManager(this)
        viewModel.posts.observe(this) { posts ->
            val mutablePosts = posts.toMutableList()
            binding.postsRecycler.adapter = PostsAdapter(mutablePosts)
        }
        binding.fabRestorePosts.setOnClickListener {
            viewModel.posts.observe(this) { posts ->
                binding.postsRecycler.adapter = PostsAdapter(posts.toMutableList())
                Toast.makeText(this, "Jañartyldy ", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.bottomNavigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED
        binding.bottomNavigation.selectedItemId = R.id.nav_friends
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
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
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                R.id.nav_friends -> true
                else -> false
            }
        }
    }
}
