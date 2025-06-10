package com.example.hobbyapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hobbyapp.Adapter.JetekshilerByCategoryAdapter
import com.example.hobbyapp.Adapter.TopJetelshilerAdapter
import com.example.hobbyapp.R
import com.example.hobbyapp.ViewModel.MainVIewModel
import com.example.hobbyapp.databinding.ActivityCategoryDetailBinding

class CategoryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryDetailBinding
    private lateinit var viewModel: MainVIewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryName = intent.getStringExtra("categoryName")

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MainVIewModel::class.java]


        viewModel.jetekshiler.observe(this) { allItems ->
            val filtered = allItems.filter { it.Category == categoryName }
            binding.viewDetCat.layoutManager = LinearLayoutManager(this)
            binding.viewDetCat.adapter = JetekshilerByCategoryAdapter(this,filtered)
        }

        viewModel.loadJetelshlier()
        binding.backBtn5.setOnClickListener {
            startActivity(Intent(this@CategoryDetailActivity, HomeActivity::class.java))
        }
    }

}

