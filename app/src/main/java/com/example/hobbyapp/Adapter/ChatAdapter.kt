package com.example.hobbyapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.ViewholderChatBinding
import com.example.hobbyapp.Domain.ChatModel
import com.example.hobbyapp.Domain.JetekshilerModel

class ChatAdapter(
    private val chatList: MutableList<JetekshilerModel>,  // Используем MutableList для возможности изменять данные
    private val onItemClick: (JetekshilerModel) -> Unit    // Лямбда-функция для обработки кликов
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    // ViewHolder для каждого элемента списка
    inner class ChatViewHolder(val binding: ViewholderChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: JetekshilerModel) {
            // Устанавливаем данные для каждого элемента списка
            binding.nameTxtCh.text = item.Name
            binding.textChat.text = item.Location

            // Загружаем изображение пользователя (аватар)
            Glide.with(binding.imgChat.context)
                .load(item.Picture) // всё верно, это resId типа Int
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imgChat)


            // Обрабатываем нажатие на элемент списка
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    // Создание нового ViewHolder для элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ViewholderChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    // Количество элементов в списке
    override fun getItemCount(): Int = chatList.size

    // Привязка данных к элементу списка
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    // Функция для добавления нового чата в список и уведомления адаптера
    fun addChat(chat: JetekshilerModel) {
        chatList.add(0, chat)  // Добавляем в начало списка
        notifyItemInserted(0)  // Уведомляем адаптер о добавлении нового элемента
    }
}
