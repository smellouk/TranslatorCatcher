package io.mellouk.translatorcatcher.di

import dagger.Component
import io.mellouk.translatorcatcher.di.api.ClientModule
import io.mellouk.translatorcatcher.di.api.ServiceModule
import io.mellouk.translatorcatcher.di.repository.RepositoryModule
import io.mellouk.translatorcatcher.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ClientModule::class,
        ServiceModule::class,
        RepositoryModule::class,
        ViewModel::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}