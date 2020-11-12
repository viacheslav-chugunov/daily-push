package com.dev.dailypush.app.base

import android.app.Application
import com.dev.dailypush.R
import com.dev.dailypush.app.di.component.AppComponent
import com.dev.dailypush.app.di.component.DaggerAppComponent
import com.dev.dailypush.app.di.module.AppModule
import com.dev.dailypush.data.repository.UserRepository

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        var appTheme: Int = -1

        fun getWindowBackgroundColor() = when (appTheme) {
            R.style.BaseTheme -> R.color.windowBackground
            R.style.ForestTheme -> R.color.windowBackground2
            R.style.LollipopTheme -> R.color.windowBackground3
            else -> R.color.windowBackground4
        }

        fun swapTheme() {
            appTheme = when (appTheme) {
                R.style.BaseTheme -> R.style.ForestTheme
                R.style.ForestTheme -> R.style.LollipopTheme
                R.style.LollipopTheme -> R.style.ComicTheme
                else -> R.style.BaseTheme
            }
        }

        fun getBackground() = when(appTheme) {
            R.style.BaseTheme -> R.drawable.app_bg_1
            R.style.ForestTheme -> R.drawable.app_bg_2
            R.style.LollipopTheme -> R.drawable.app_bg_3
            else -> R.drawable.app_bg_4
        }
    }

    override fun onCreate() {
        super.onCreate()
        appTheme = UserRepository(this).getTheme()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}