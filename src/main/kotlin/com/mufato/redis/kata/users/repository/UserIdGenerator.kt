package com.mufato.redis.kata.users.repository

interface UserIdGenerator {
    suspend fun nextId():Long
}
