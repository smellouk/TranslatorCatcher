package io.mellouk.translatorcatcher.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.mellouk.translatorcatcher.ui.MainViewModel
import javax.inject.Singleton

@Module
interface ViewModel {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Singleton
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}