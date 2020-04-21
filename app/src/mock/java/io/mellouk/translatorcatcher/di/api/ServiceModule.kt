package io.mellouk.translatorcatcher.di.api

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.mellouk.translatorcatcher.domain.api.WordsService
import javax.inject.Singleton

@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideWordsService(
        context: Context,
        gson: Gson
    ): WordsService = WordsServiceImpl(context, gson)
}