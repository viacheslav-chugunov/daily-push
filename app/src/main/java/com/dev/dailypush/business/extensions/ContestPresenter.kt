package com.dev.dailypush.business.extensions

import com.dev.dailypush.app.presentation.contest.ContestPresenter
import com.dev.dailypush.app.presentation.main.MainActivity
import com.dev.dailypush.business.consts.Lang
import com.dev.dailypush.data.db.helper.BlessingHelper
import kotlin.random.Random

fun ContestPresenter.tryToQuitIfLostOnContestBlessing() : Boolean {
    if (blessingRepository.getBlessing() == BlessingHelper.Blessings.CONTEST) {
        viewState.startActivity(MainActivity::class.java)
        return true
    }
    return false
}

fun ContestPresenter.tryToLostCoinOnCoinsBlessing() {
    if (blessingRepository.getBlessing() == BlessingHelper.Blessings.COINS) {
        userRepository.spendCoins(1)
    }
}

fun ContestPresenter.tryToUnlockContestBlessing() {
    if (!blessingRepository.isBlessingUnlocked(BlessingHelper.Blessings.CONTEST) && rightAnswersInRow >= 35) {
        blessingRepository.unlockBlessing(BlessingHelper.Blessings.CONTEST)
        viewState.showToastOnUnlockContestBlessing()
    }
}

fun ContestPresenter.tryToUnlockScoreBlessing() {
    if (!blessingRepository.isBlessingUnlocked(BlessingHelper.Blessings.SCORE) && scoreRepository.getScore() >= 666) {
        blessingRepository.unlockBlessing(BlessingHelper.Blessings.SCORE)
        viewState.showToastOnUnlockScoreBlessing()
    }
}

fun ContestPresenter.tryToUnlockCoinsBlessing() {
    if (!blessingRepository.isBlessingUnlocked(BlessingHelper.Blessings.COINS) && userRepository.getCoinsCount() >= 500) {
        blessingRepository.unlockBlessing(BlessingHelper.Blessings.COINS)
        viewState.showToastOnUnlockCoinsBlessing()
    }
}

fun ContestPresenter.tryToInitScoreBlessing() {
    if (blessingRepository.getBlessing() == BlessingHelper.Blessings.SCORE) {
        scoreOnRight *= 2
        scoreOnWrong *= 3
    }
}

fun ContestPresenter.tryToInitContestBlessing() {
    if (blessingRepository.getBlessing() == BlessingHelper.Blessings.CONTEST)
        maxContestUnits == options.size
}

fun ContestPresenter.getScoreOnRightBy(score: Int) = when(score) {
    in 0..100 -> 3
    in 101..200 -> 4
    in 201..500 -> 5
    in 501..1000 -> 6
    in 1001..1500 -> 7
    in 1501..2000 -> 8
    in 2001..5000 -> 9
    in 5001..10000 -> 10
    else -> 11
}

fun ContestPresenter.getScoreOnWrongBy(score: Int) = when(score) {
    in 0..100 -> -1
    in 101..200 -> -3
    in 201..500 -> -5
    in 501..1000 -> -7
    in 1001..1500 -> -9
    in 1501..2000 -> -11
    in 2001..5000 -> -15
    in 5001..10000 -> -20
    else -> -35
}

fun ContestPresenter.getCoinsOnRightBy(score: Int) = when(score) {
    in 0..500 -> 1
    in 501..1000 -> 2
    in 1001..2000 -> 3
    in 2001..5000 -> 4
    else -> 5
}

fun ContestPresenter.tryToUpdateContestOnLuckBlessing() : Boolean {
    if (blessingRepository.getBlessing() == BlessingHelper.Blessings.LUCK && Random.nextInt(1, 10) == 1) {
        return true
    }
    return false
}

fun ContestPresenter.tryToUpdateLangOnMysteryBlessing() {
    if (blessingRepository.getBlessing() == BlessingHelper.Blessings.MYSTERY)
        setup(listOf(Lang.RU, Lang.EN).shuffled().first())
}