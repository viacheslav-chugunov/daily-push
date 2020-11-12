package com.dev.dailypush.app.presentation.contest

import com.dev.dailypush.app.adapter.TranslateAdapter
import com.dev.dailypush.business.models.TranslateOption
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface ContestView : MvpView {
    fun updateContest(answer: String, options: List<TranslateOption>, listener: TranslateAdapter.Listener)
    fun updateScore()
    fun hidePrevRightAnswer()
    fun showPrevRightAnswer(option: TranslateOption)
    fun showToastIfCannotPurchase()
    fun showToastOnUnlockScoreBlessing()
    fun showToastOnUnlockCoinsBlessing()
    fun showToastOnUnlockContestBlessing()
    fun startActivity(newActivity: Class<*>)
}