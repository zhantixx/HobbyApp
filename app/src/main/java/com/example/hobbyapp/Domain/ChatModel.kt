package com.example.hobbyapp.Domain

    data class ChatModel(
        val userId: String,
        val userName: String,
        val avatarUrl: String,
        val lastMessage: String,
        val timestamp: Long

    )

