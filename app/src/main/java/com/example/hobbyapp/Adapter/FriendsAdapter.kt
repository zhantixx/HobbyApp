package com.example.hobbyapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hobbyapp.Activity.FriendProfileActivity
import com.example.hobbyapp.Data.FriendEntity
import com.example.hobbyapp.R

class FriendsAdapter(private val list: List<FriendEntity>) :
    RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {

    inner class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: ImageView = itemView.findViewById(R.id.img)
        val name: TextView = itemView.findViewById(R.id.titleTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friend, parent, false)
        return FriendViewHolder(view)

    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friend = list[position]
        holder.avatar.setImageResource(friend.avatarResId)
        holder.name.text = friend.name

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, FriendProfileActivity::class.java)
            intent.putExtra("friendName", friend.name)
            intent.putExtra("avatarResId", friend.avatarResId) // ✅ вот этого не хватало
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int = list.size
}
