package com.dev.dailypush.app.presentation.main

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainView : MvpView {
    fun showNoTicketsMessage()
}