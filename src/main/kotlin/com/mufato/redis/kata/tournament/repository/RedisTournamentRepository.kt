package com.mufato.redis.kata.tournament.repository

import io.lettuce.core.api.sync.RedisCommands

class RedisTournamentRepository(private val connection: RedisCommands<String, String>) : TournamentRepository {
    override fun getAll(): Collection<RankingPosition> {
        TODO("get all Ranking Positions")
    }

    override fun addPoints(userId: Long, pointsAmount: Double) {
        TODO("to $userId add $pointsAmount")
    }

    override fun getPositionAndPointsFor(userId: Long): RankingPosition {
        TODO("retunr the RankingPosition for $userId")
    }

//    override fun promoteBestXusersToLeague(numberOfUsers: Int, newTournamentName: String) {
//        TODO("take the best $numberOfUsers and move them to $newTournamentName")
//    }
}
