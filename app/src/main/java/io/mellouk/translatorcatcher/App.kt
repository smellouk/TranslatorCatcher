package io.mellouk.translatorcatcher

import android.app.Application
import io.mellouk.translatorcatcher.di.AppComponent
import io.mellouk.translatorcatcher.di.AppModule
import io.mellouk.translatorcatcher.di.DaggerAppComponent
import io.mellouk.translatorcatcher.di.api.ClientModule

class App : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .clientModule(
                ClientModule(
                    BuildConfig.BASE_URL,
                    BuildConfig.DEBUG
                )
            )
            .build()
    }

    fun getComponent() = appComponent
}