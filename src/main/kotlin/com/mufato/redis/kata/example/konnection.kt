package com.mufato.redis.kata.example

import io.lettuce.core.RedisClient
import io.lettuce.core.RedisURI
import io.lettuce.core.RedisURI.Builder;
import io.lettuce.core.api.async.RedisAsyncCommands
import io.lettuce.core.api.coroutines
import io.lettuce.core.api.reactive.RedisReactiveCommands
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main(args: Array<String>) {

    val IP = "localhost"
    val PORT = 16379
    val DB = 0

    println("Connection to Redis")
    val url: RedisURI = Builder.redis(IP, PORT)
        .withDatabase(DB)
        .build()
    val redisClient = RedisClient.create(url)
    val statefulRedisConnection = redisClient.connect()

    println("Obtains async connection")
    val syncConnection = statefulRedisConnection.coroutines()
//    val syncConnection: RedisReactiveCommands<String, String> = statefulRedisConnection.reactive()
//    val syncConnection: RedisAsyncCommands<String, String> = statefulRedisConnection.async()

    coroutineScope {
        launch {
            println("saving name")
            syncConnection.set("name", "lucas")

            val value = syncConnection.get("name")
            println("name is $value")
        }
    }

    statefulRedisConnection.close()
    redisClient.shutdown()

}