package com.example.hobbyapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.hobbyapp.Activity.DetailActivity
import com.example.hobbyapp.Domain.JetekshilerModel
import com.example.hobbyapp.databinding.ViewholderCategoryBinding
import com.example.hobbyapp.databinding.ViewholderTopJetekshlierBinding

class TopJetelshilerAdapter(val items:MutableList<JetekshilerModel>):RecyclerView.Adapter<TopJetelshilerAdapter.Viewholder>()   {

    private var context:Context?=null

    class Viewholder(val binding: ViewholderTopJetekshlierBinding) :
        RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopJetelshilerAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderTopJetekshlierBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int=items.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binding.nameTxt.text=items[position].Name
        holder.binding.specTxt.text=items[position].Special
        holder.binding.ratTxt.text=items[position].Rating.toString()

        Glide.with(holder.itemView.context)
            .load(items[position].Picture)
            .apply { RequestOptions().transform(CenterCrop()) }
            .into(holder.binding.imgJet)
        holder.itemView.setOnClickListener{
            val intent= Intent(context,DetailActivity::class.java)
            intent.putExtra("object",items[position])
            context?.startActivity(intent)
        }
    }
}