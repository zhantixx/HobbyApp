package com.example.hobbyapp.Adapter

import android.content.Intent
import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.hobbyapp.Domain.JetekshilerModel
import com.example.hobbyapp.databinding.ViewholderDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.hobbyapp.Activity.CategoryDetailActivity
import com.example.hobbyapp.Activity.DetailActivity

class JetekshilerByCategoryAdapter(
    private val context: Context,
    private val items: List<JetekshilerModel>
) : RecyclerView.Adapter<JetekshilerByCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewholderDetailBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholderDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.nameTxtDet.text = item.Name
        holder.binding.specTxtDet.text = item.Special
        holder.binding.ratTxt2.text = item.Rating.toString()
        holder.binding.ratingBar2.rating = item.Rating.toFloat()

        Glide.with(holder.itemView.context)
            .load(item.Picture)
            .into(holder.binding.detImg)

        holder.itemView.setOnClickListener{
            val intent= Intent(context, DetailActivity::class.java)
            intent.putExtra("object",items[position])
            context?.startActivity(intent)
        }
    }
}
