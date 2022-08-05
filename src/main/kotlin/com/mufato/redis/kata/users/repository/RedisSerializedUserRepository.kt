package com.mufato.redis.kata.users.repository

import io.lettuce.core.api.sync.RedisCommands

class RedisSerializedUserRepository(private val connection: RedisCommands<String, String>) : UserRepository {
    override fun save(user: User) {
        TODO("Not yet implemented")
    }

    override fun getById(userId: Long): User {
        TODO("Not yet implemented")
    }

    override fun getByUsername(username: String): User {
        TODO("Not yet implemented")
    }
}
