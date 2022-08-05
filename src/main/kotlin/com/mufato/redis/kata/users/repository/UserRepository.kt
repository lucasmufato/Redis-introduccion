package com.mufato.redis.kata.users.repository

interface UserRepository {
    fun save(user: User)
    fun getById(userId: Long): User?
    fun getByUsername(username: String): User?
}
