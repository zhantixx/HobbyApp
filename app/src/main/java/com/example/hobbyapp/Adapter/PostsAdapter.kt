package com.example.hobbyapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hobbyapp.Data.PostEntity
import com.example.hobbyapp.R

class PostsAdapter(
    private val originalList: MutableList<PostEntity>
) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val desc: TextView = itemView.findViewById(R.id.description)
        val applyBtn: ImageButton = itemView.findViewById(R.id.btnApply)
        val dismissBtn: ImageButton = itemView.findViewById(R.id.btnDismiss)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = originalList[position]

        if (!post.imageUri.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(post.imageUri)
                .placeholder(R.drawable.experience)
                .into(holder.image)
        } else {
            holder.image.setImageResource(post.imageResId)
        }

        holder.title.text = post.title
        holder.desc.text = post.description

        // Зелёная кнопка
        holder.applyBtn.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Siz postqa jauap berdiñiz ", Toast.LENGTH_SHORT).show()
            removeWithAnimation(holder.adapterPosition, holder.itemView)
        }

        // Красная кнопка
        holder.dismissBtn.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Bul post sizge qyzyq emes ", Toast.LENGTH_SHORT).show()
            removeWithAnimation(holder.adapterPosition, holder.itemView)
        }
    }

    override fun getItemCount(): Int = originalList.size

    private fun removeWithAnimation(position: Int, view: View) {
        view.animate()
            .alpha(0f)
            .setDuration(300)
            .withEndAction {
                originalList.removeAt(position)
                notifyItemRemoved(position)
            }
            .start()
    }
}
