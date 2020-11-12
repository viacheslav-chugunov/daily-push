package com.dev.dailypush.app.di.module

import com.dev.dailypush.app.presentation.contest.ContestPresenter
import com.dev.dailypush.app.presentation.main.MainPresenter
import com.dev.dailypush.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
class PresenterModule {

    @Provides
    fun provideContestPresenter(userRepository: UserRepository, appWordRepository: AppWordRepository,
                                userWordRepository: UserWordRepository, scoreRepository: ScoreRepository,
                                blessingRepository: BlessingRepository) : ContestPresenter =
        ContestPresenter(userRepository, appWordRepository, userWordRepository, scoreRepository, blessingRepository)

    @Provides
    fun provideMainPresenter(userRepository: UserRepository): MainPresenter =
        MainPresenter(userRepository)
}
