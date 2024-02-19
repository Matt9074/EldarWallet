package com.example.eldarfinal.login.data

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    suspend fun getAllUsers(): List<UserEntity> {
        return userDao.getAllUsers()
    }

    suspend fun getUserByFullNameAndDni(fullName: String, dni: String): UserEntity? {
        return userDao.getUserByFullNameAndDni(fullName, dni)
    }
}
