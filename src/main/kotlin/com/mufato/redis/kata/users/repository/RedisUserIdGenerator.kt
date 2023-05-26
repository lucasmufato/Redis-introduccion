package com.mufato.redis.kata.users.repository

import io.lettuce.core.api.coroutines.RedisCoroutinesCommands

class RedisUserIdGenerator(private val redisConnection: RedisCoroutinesCommands<String, String>) : UserIdGenerator {

    override suspend fun nextId(): Long {
        TODO("return next user id")
    }
}
