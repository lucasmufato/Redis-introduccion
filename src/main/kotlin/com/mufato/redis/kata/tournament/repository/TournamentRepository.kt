package com.mufato.redis.kata.tournament.repository

interface TournamentRepository {

    fun getAll():Collection<RankingPosition>
    fun addPoints(userId: Long, pointsAmount:Double)
    fun getPositionAndPointsFor(userId:Long): RankingPosition?
//    fun promoteBestXusersToLeague(numberOfUsers:Int, newTournamentName:String)

}
