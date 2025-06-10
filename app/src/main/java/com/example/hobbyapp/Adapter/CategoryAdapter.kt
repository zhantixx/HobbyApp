package com.example.hobbyapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hobbyapp.Domain.CategoryModel
import com.example.hobbyapp.databinding.ActivityIntroBinding
import com.example.hobbyapp.databinding.ViewholderCategoryBinding


class CategoryAdapter(
    private val items: MutableList<CategoryModel>,
    private val onCategoryClick: (CategoryModel) -> Unit // <-- передаем весь объект
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleTxt.text = item.name

        Glide.with(context)
            .load(item.imageResId)
            .into(holder.binding.img)

        holder.itemView.setOnClickListener {
            onCategoryClick(item) // передаём весь объект
        }
    }
}
