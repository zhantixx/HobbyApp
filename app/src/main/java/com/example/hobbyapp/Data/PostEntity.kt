package com.example.hobbyapp.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val imageUri: String? = null,
    val imageResId: Int = 0,
    val authorName: String = ""

)

