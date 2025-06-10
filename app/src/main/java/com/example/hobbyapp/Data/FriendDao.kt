package com.example.hobbyapp.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FriendDao {
    @Query("SELECT * FROM friends")
    fun getAll(): LiveData<List<FriendEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(friends: List<FriendEntity>)

    @Query("SELECT COUNT(*) FROM friends")
    suspend fun getCount(): Int
}
