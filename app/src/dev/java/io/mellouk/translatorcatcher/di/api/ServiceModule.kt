package io.mellouk.translatorcatcher.di.api

import dagger.Module
import dagger.Provides
import io.mellouk.translatorcatcher.domain.api.WordsService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideWordsService(retrofit: Retrofit): WordsService =
        retrofit.create(WordsService::class.java)
}