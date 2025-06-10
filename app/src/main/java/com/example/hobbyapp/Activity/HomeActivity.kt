package com.example.hobbyapp.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hobbyapp.Adapter.CategoryAdapter
import com.example.hobbyapp.Adapter.TopJetelshilerAdapter
import com.example.hobbyapp.Domain.JetekshilerModel
import com.example.hobbyapp.R
import com.example.hobbyapp.ViewModel.MainVIewModel
import com.example.hobbyapp.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.LabelVisibilityMode

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: MainVIewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MainVIewModel::class.java]

        initCategory()
        initTopJetekshiler()
        initSearch()

        binding.bottomNavigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {

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
                R.id.nav_friends -> {
                    startActivity(Intent(this, FriendsActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun initSearch() {
        binding.editTextText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.filterJetekshiler(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun initTopJetekshiler() {
        binding.apply {
            progressBar.visibility = View.VISIBLE

            viewModel.jetekshiler.observe(this@HomeActivity, Observer {
                viewModel.filterJetekshiler("") // отобразить все
                progressBar.visibility = View.GONE
            })

            viewModel.filteredJetekshiler.observe(this@HomeActivity, Observer { filteredList ->
                viewTop.layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                viewTop.adapter = TopJetelshilerAdapter(filteredList.toMutableList())
            })


            listTxt.setOnClickListener {
                startActivity(Intent(this@HomeActivity, TopListActivity::class.java))
            }

        }

        viewModel.loadJetelshlier()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer { categories ->
            binding.viewCat.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.viewCat.adapter = CategoryAdapter(categories) { selectedCategory ->
                val intent = Intent(this, CategoryDetailActivity::class.java)
                intent.putExtra("categoryName", selectedCategory.name)
                startActivity(intent)
            }

            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategory()
    }
}
