package io.mellouk.translatorcatcher.domain.repository

class ScoreRepository {
    private var score = 0

    fun resetScore() {
        score = 0
    }

    @Synchronized
    fun increase() {
        score++
    }

    fun currentScore() = score
}