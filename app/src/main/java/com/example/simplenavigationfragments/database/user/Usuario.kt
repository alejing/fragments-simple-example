package com.example.simplenavigationfragments.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "password") val password: String
)
