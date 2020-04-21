package io.mellouk.translatorcatcher.domain.usecase.nextround

import io.mellouk.translatorcatcher.domain.BaseUseCase
import io.mellouk.translatorcatcher.domain.model.Round
import io.mellouk.translatorcatcher.domain.model.Word
import io.mellouk.translatorcatcher.domain.repository.WordsRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrepareNextRoundUseCase @Inject constructor(
    private val repository: WordsRepository
) : BaseUseCase<PrepareDataState> {
    override fun buildObservable(): Observable<PrepareDataState> =
        Single.just(repository.getWords()).toObservable()
            .map { words ->
                PrepareDataState.Successful(
                    prepareRound(words.toMutableList())
                )
            }

    private fun randomizer() = (1..RANGE).random()

    private fun isItWiningChance() = randomizer() == WINING_CHANCE

    private fun prepareRound(words: MutableList<Word>): Round {
        val staticWord = words.random()
        val movableWord = if (isItWiningChance()) {
            staticWord
        } else {
            words.remove(staticWord)
            words.random()
        }

        return Round(staticWord, movableWord)
    }
}

//Represents chance to have a similar word which is 33%
private const val RANGE = 3
private const val WINING_CHANCE = 2