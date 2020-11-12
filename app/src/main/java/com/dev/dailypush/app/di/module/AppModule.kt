package com.dev.dailypush.app.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
class AppModule(private val app: Application) {

    @Provides
    fun provideAppContext() : Context =
        app.applicationContext

}