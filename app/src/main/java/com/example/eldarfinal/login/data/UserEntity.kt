package com.example.eldarfinal.login.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")

data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val dni: String
)
