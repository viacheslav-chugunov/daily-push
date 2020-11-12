package com.dev.dailypush.app.presentation.main

import android.content.Context
import android.content.Intent
import com.dev.dailypush.app.base.App
import com.dev.dailypush.app.presentation.contest.ContestActivity
import com.dev.dailypush.business.extensions.startActivityInNewTask
import com.dev.dailypush.data.repository.UserRepository
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter(private var repository: UserRepository) : MvpPresenter<MainView>() {

    fun startContestIfHaveTickets(context: Context, lang: String) =
        if (repository.spendTicket()) {
            val intent = Intent(context, ContestActivity::class.java)
            intent.putExtra(ContestActivity.Extra.LANG_TO_TRANSLATE, lang)
            context.startActivity(intent)
        } else {
            viewState.showNoTicketsMessage()
        }

    fun updateTheme() {
        App.swapTheme()
        repository.setTheme(App.appTheme)
    }
}