package com.example.hobbyapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hobbyapp.Activity.HomeActivity
import com.example.hobbyapp.Adapter.CategoryAdapter
import com.example.hobbyapp.Adapter.TopJetelshilerAdapter2
import com.example.hobbyapp.R
import com.example.hobbyapp.ViewModel.MainVIewModel
import com.example.hobbyapp.databinding.ActivityTopListBinding

 class TopListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopListBinding
    private lateinit var viewModel: MainVIewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTopListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainVIewModel::class.java]

        initTopJetekshiler()

        }

    private fun initTopJetekshiler(){
        binding.apply {
            binding.progressBar33.visibility= View.VISIBLE
            viewModel.jetekshiler.observe(this@TopListActivity, Observer{
                binding.viewTop.layoutManager=
                    LinearLayoutManager(this@TopListActivity, LinearLayoutManager.VERTICAL,false)
                binding.viewTop.adapter= TopJetelshilerAdapter2(it)
                binding.progressBar33.visibility= View.GONE
            })
            viewModel.loadJetelshlier()

            binding.backBtn2.setOnClickListener {
                startActivity(Intent(this@TopListActivity, HomeActivity::class.java))
            }
        }
    }
}


