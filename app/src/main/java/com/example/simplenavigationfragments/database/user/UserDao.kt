package com.example.simplenavigationfragments.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAll(): List<Usuario>

    @Query("SELECT * FROM users WHERE user= :user and password= :password")
    suspend fun getUserById(user: String, password: String): Usuario

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: Usuario)
}