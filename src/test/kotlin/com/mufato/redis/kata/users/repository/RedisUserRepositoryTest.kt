package com.mufato.redis.kata.users.repository

import io.lettuce.core.RedisClient
import io.lettuce.core.api.sync.RedisCommands
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import redis.embedded.RedisServer
import kotlin.test.assertEquals

abstract class RedisUserRepositoryTest{

    protected lateinit var userRepository: UserRepository

    @BeforeEach
    internal fun setUp() {
        givenARepository()
    }

    abstract fun givenARepository()

    @Test
    fun `saves a user and returns it by its id`() {
        val user = User(1L, "lucas", "lucas@mufato.com")
        userRepository.save(user)
        val returnedUser = userRepository.getById(1)
        assertEquals(user, returnedUser)
    }

    @Test
    fun `saves a user and returns it by its username`() {
        val user = User(1L, "lucas", "lucas@mufato.com")
        userRepository.save(user)
        val returnedUser = userRepository.getByUsername("lucas")
        assertEquals(user, returnedUser)
    }

}

class RedisHashUserRepositoryTest: RedisUserRepositoryTest() {
    override fun givenARepository() {
        userRepository = RedisHashUserRepository(redisConnection)
        redisConnection.flushall()
    }

    private companion object{
        private lateinit var redisServer: RedisServer
        private lateinit var redisClient: RedisClient
        lateinit var redisConnection: RedisCommands<String, String>

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

class RedisSerializedUserRepositoryTest: RedisUserRepositoryTest() {

    /** Recomendacion de serializado:
     * usar:
    import kotlinx.serialization.SerialName
    import kotlinx.serialization.Serializable
    import kotlinx.serialization.json.Json
    import kotlinx.serialization.encodeToString
    import kotlinx.serialization.decodeFromString
    Json.encodeToString(UserItem.from(user))
    Json.decodeFromString<UserItem>(item).toUser()
     *

     **/

    override fun givenARepository() {
        userRepository = RedisSerializedUserRepository(redisConnection)
        redisConnection.flushall()
    }

    private companion object{
        private lateinit var redisServer: RedisServer
        private lateinit var redisClient: RedisClient
        lateinit var redisConnection: RedisCommands<String, String>

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
