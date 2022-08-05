package com.mufato.redis.kata

import io.lettuce.core.RedisClient
import io.lettuce.core.api.coroutines
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import redis.embedded.RedisServer
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class PruebaTest {

    @Test
    internal fun `test de que te anda algo mas o menos`() {
        assertTrue { true }
    }

    @Test
    internal fun `test de conexion a redis embebido`() = runBlocking{
        redisConnection.set("hola", "mundo")
        val mundo = redisConnection.get("hola")!!
        assertEquals("mundo", mundo)
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
