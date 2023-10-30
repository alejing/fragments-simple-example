package com.example.simplenavigationfragments.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM usuario")
    suspend fun getAll(): List<Usuario>

    @Query("SELECT * FROM usuario WHERE id = id")
    suspend fun getUserById(id: Int): Usuario

    @Insert
    suspend fun insertUser(user: Usuario)
}