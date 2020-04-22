package io.mellouk.translatorcatcher.domain.usecase.matchwords

import io.mellouk.translatorcatcher.domain.BaseParamUseCase
import io.mellouk.translatorcatcher.domain.repository.ScoreRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchWordsUseCase @Inject constructor(
    private val scoreRepository: ScoreRepository
) : BaseParamUseCase<MatchWordsParams, MatchWordsDataState> {
    override fun buildObservable(params: MatchWordsParams): Observable<MatchWordsDataState> =
        Single.just(params).toObservable().map {
            with(params) {
                val isSame = compareWords(staticWordId, fallingWordId)
                if (isSame == hasSelectedCorrect) {
                    scoreRepository.increase()
                    MatchWordsDataState.Successful
                } else {
                    MatchWordsDataState.Fail
                }
            }
        }

    private fun compareWords(staticWordId: String, fallingWordId: String) =
        staticWordId == fallingWordId
}