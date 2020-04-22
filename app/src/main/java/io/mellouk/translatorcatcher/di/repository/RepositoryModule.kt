package io.mellouk.translatorcatcher.di.repository

import dagger.Module
import dagger.Provides
import io.mellouk.translatorcatcher.domain.api.WordsService
import io.mellouk.translatorcatcher.domain.repository.ScoreRepository
import io.mellouk.translatorcatcher.domain.repository.WordsRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideWordsRepository(wordsService: WordsService): WordsRepository =
        WordsRepository(wordsService)

    @Provides
    @Singleton
    fun provideScoreRepository(): ScoreRepository = ScoreRepository()
}