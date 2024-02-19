package com.example.eldarfinal.login.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE fullName = :fullName AND dni = :dni")
    suspend fun getUserByFullNameAndDni(fullName: String, dni: String): UserEntity?
}