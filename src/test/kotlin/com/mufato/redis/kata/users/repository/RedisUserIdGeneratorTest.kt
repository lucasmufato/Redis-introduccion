package com.mufato.redis.kata.users.repository

import io.lettuce.core.RedisClient
import io.lettuce.core.api.coroutines
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import redis.embedded.RedisServer
import kotlin.test.assertEquals

internal class RedisUserIdGeneratorTest{

    private lateinit var userIdGenerator: UserIdGenerator

    @BeforeEach
    internal fun setUp():Unit = runBlocking{
        redisConnection.flushall()
    }

    @Test
    internal fun `some test`() = runBlocking{
        givenArepository()
        //continuar
    }

    private fun givenArepository(){
        userIdGenerator = RedisUserIdGenerator(redisConnection)
    }

    companion object {
        private lateinit var redisServer: RedisServer
        private lateinit var redisClient: RedisClient
        private lateinit var redisConnection: RedisCoroutinesCommands<String, String>

        @BeforeAll
        @JvmStatic
        fun setup() {
            redisServer = RedisServer.builder().port(12345).build()
            redisServer.start()
            redisClient = RedisClient.create("redis://localhost:${redisServer.ports()!![0]}")
            redisConnection = redisClient.connect().coroutines()
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            redisClient.shutdown()
            redisServer.stop()
        }
    }

}
