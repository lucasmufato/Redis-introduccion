package com.mufato.redis.kata.tournament.repository

import io.lettuce.core.RedisClient
import io.lettuce.core.api.sync.RedisCommands
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import redis.embedded.RedisServer

internal class RedisTournamentRepositoryTest{

    private lateinit var repository: RedisTournamentRepository

    @BeforeEach
    internal fun setUp() {
        redisConnection.flushall()
    }

    @Test
    internal fun `some test`() {
        givenARepository()
    }

    private fun givenARepository() {
        repository = RedisTournamentRepository(redisConnection)
    }

    companion object {
        private lateinit var redisServer: RedisServer
        private lateinit var redisClient: RedisClient
        private lateinit var redisConnection: RedisCommands<String, String>

        @BeforeAll
        @JvmStatic
        fun setup() {
            redisServer = RedisServer.builder().port(12345).build()
            redisServer.start()
            redisClient = RedisClient.create("redis://localhost:${redisServer.ports()!![0]}")
            redisConnection = redisClient.connect().sync()
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            redisClient.shutdown()
            redisServer.stop()
        }
    }
}
