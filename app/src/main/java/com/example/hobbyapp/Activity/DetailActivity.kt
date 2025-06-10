package com.example.hobbyapp.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hobbyapp.Adapter.ScheduleAdapter
import com.example.hobbyapp.Domain.JetekshilerModel
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.ActivityDetailBinding
import com.example.hobbyapp.databinding.ActivityIntroBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding
    private lateinit var item:JetekshilerModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getBundle()

        }

    private fun getBundle() {
        item=intent.getParcelableExtra("object")!!


        binding.apply {

            titleTtx1.text=item.Name
            specTxt1.text=item.Special
            peopTxt.text=item.peoples
            bioTxt.text=item.Biography
            addTxt1.text=item.Address
            expTxt.text=item.Experience.toString()+" Jyl"
            ratTxt1.text="${item.Rating}"
            backBtn.setOnClickListener{
                startActivity(Intent(this@DetailActivity, HomeActivity::class.java))
            }
            instBtn.setOnClickListener {
                val scheduleList = item.schedule
                if (!scheduleList.isNullOrEmpty()) {
                    val dialogView = layoutInflater.inflate(R.layout.layout_schedule_dialog, null)
                    val recyclerView = dialogView.findViewById<RecyclerView>(R.id.scheduleRecycler)

                    recyclerView.layoutManager = LinearLayoutManager(this@DetailActivity)
                    recyclerView.adapter = ScheduleAdapter(scheduleList)

                    val dialog = android.app.AlertDialog.Builder(this@DetailActivity)
                        .setView(dialogView)
                        .setPositiveButton("Jabu", null)
                        .create()

                    dialog.show()
                } else {
                    Toast.makeText(this@DetailActivity, "Keste jok", Toast.LENGTH_SHORT).show()
                }
            }

            messBtn.setOnClickListener {
                val intent = Intent(this@DetailActivity, ChatActivity::class.java)
                intent.putExtra("userId", item.id.toString())
                intent.putExtra("userName", item.Name)
                intent.putExtra("avatarResId", item.Picture)
                startActivity(intent)
            }

            direcBtn.setOnClickListener {
                val intent=Intent(Intent.ACTION_VIEW,
                    Uri.parse(item.Location))
                startActivity(intent)
            }
            direcBtn.setOnClickListener {
                try {
                    val uri = Uri.parse("geo:0,0?q=" + Uri.encode(item.Address))
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this@DetailActivity, "Google Maps qosymshasy tabylmady", Toast.LENGTH_SHORT).show()
                }
            }
            makeBtn.setOnClickListener {
                Toast.makeText(this@DetailActivity, "Jauap kútıńız, sizben jaqynda baılanısqa shyǵady", Toast.LENGTH_LONG).show()
            }


            shareBtn.setOnClickListener{
                val intent=Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT,item.Name)
                intent.putExtra(Intent.EXTRA_TEXT,item.Name + " " + item.Address + " " + item.Mobile
                )
                startActivity(Intent.createChooser(intent,"Choose one"))

            }
            Glide.with(this@DetailActivity)
                .load(item.Picture)
                .into(imgTop)




        }
    }
}
