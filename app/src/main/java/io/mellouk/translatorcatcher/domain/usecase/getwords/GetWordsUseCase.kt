package io.mellouk.translatorcatcher.domain.usecase.getwords

import io.mellouk.translatorcatcher.domain.BaseUseCase
import io.mellouk.translatorcatcher.domain.repository.WordsRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWordsUseCase @Inject constructor(
    private val repository: WordsRepository,
    private val wordMapper: WordMapper
) : BaseUseCase<GetWordsDataState> {
    override fun buildObservable(): Observable<GetWordsDataState> =
        repository.getRemoteWords().toObservable().map { dtos ->
            wordMapper.map(dtos)
        }.doOnNext { words ->
            repository.saveWords(words)
        }.doOnError { throwable ->
            GetWordsDataState.Fail(throwable)
        }.map {
            GetWordsDataState.Successful
        }
}