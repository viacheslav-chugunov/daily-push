package com.dev.dailypush.app.di.module

import android.content.Context
import com.dev.dailypush.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
class RepositoryModule {

    @Provides
    fun provideScoreRepository(context: Context): ScoreRepository =
        ScoreRepository(context)

    @Provides
    fun provideWordRepository(context: Context): AppWordRepository =
        AppWordRepository(context)

    @Provides
    fun provideUserRepository(context: Context): UserRepository =
        UserRepository(context)

    @Provides
    fun provideBlessingRepository(context: Context): BlessingRepository =
        BlessingRepository(context)

    @Provides
    fun provideUserWordRepository(context: Context): UserWordRepository =
        UserWordRepository(context)
}