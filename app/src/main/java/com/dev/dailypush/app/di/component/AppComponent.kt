package com.dev.dailypush.app.di.component

import com.dev.dailypush.app.presentation.addition.WordAdditionActivity
import com.dev.dailypush.app.presentation.blessing.BlessingListFragment
import com.dev.dailypush.app.presentation.blessing.BlessingActivity
import com.dev.dailypush.app.presentation.shop.ShopListFragment
import com.dev.dailypush.app.presentation.user.StockFragment
import com.dev.dailypush.app.di.module.AppModule
import com.dev.dailypush.app.di.module.PresenterModule
import com.dev.dailypush.app.di.module.RepositoryModule
import com.dev.dailypush.app.presentation.contest.ContestActivity
import com.dev.dailypush.app.presentation.contest.ContestPresenter
import com.dev.dailypush.app.presentation.main.MainActivity
import com.dev.dailypush.app.presentation.main.MainPresenter
import com.dev.dailypush.app.presentation.shop.TicketsShopActivity
import com.dev.dailypush.app.presentation.user.ScoreFragment
import com.dev.dailypush.app.presentation.split.SplitActivity
import com.dev.dailypush.app.presentation.contest.TranslateListFragment
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class, RepositoryModule::class, PresenterModule::class])
@Singleton
interface AppComponent {

    // activities
    fun inject(activity: MainActivity)
    fun inject(activity: ContestActivity)
    fun inject(activity: TicketsShopActivity)
    fun inject(activity: WordAdditionActivity)

    // fragment
    fun inject(fragment: ScoreFragment)
    fun inject(fragment: TranslateListFragment)
    fun inject(fragment: StockFragment)
    fun inject(fragment: ShopListFragment)
    fun inject(fragment: BlessingListFragment)

    // presenter
    fun inject(presenter: ContestPresenter)
    fun inject(presenter: MainPresenter)
}