package com.dev.dailypush.app.presentation.contest

import com.dev.dailypush.app.adapter.TranslateAdapter
import com.dev.dailypush.app.base.App
import com.dev.dailypush.business.models.TranslateOption
import com.dev.dailypush.app.presentation.main.MainActivity
import com.dev.dailypush.business.consts.Lang.EN
import com.dev.dailypush.business.consts.Lang.RU
import com.dev.dailypush.business.extensions.*
import com.dev.dailypush.data.db.helper.BlessingHelper
import com.dev.dailypush.data.repository.*
import moxy.MvpPresenter
import javax.inject.Inject
import kotlin.random.Random

class ContestPresenter(var userRepository: UserRepository, var appWordRepository: AppWordRepository,
                       var userWordRepository: UserWordRepository, var scoreRepository: ScoreRepository,
                       var blessingRepository: BlessingRepository
) : MvpPresenter<ContestView>(), TranslateAdapter.Listener {

    lateinit var options: List<TranslateOption>
    var lang = ""
    val usersWrongOptions = mutableListOf<TranslateOption>()
    var scoreOnRight = 0
    var scoreOnWrong = 0
    var coinsOnRight = 0
    var currentWordIndex = 0
    var maxContestUnits = 50
    private var percent60Cost = 8
    private var percent40Cost = 15
    private var isReliefEstablished = false
    var rightAnswersInRow = 0

    companion object { const val MAX_OPTIONS = 5 }

    fun setup(lang: String) {
        when (lang) {
            RU -> {
                options = appWordRepository.getTranslateOptions() +
                        userWordRepository.getTranslateOptions()
                this.lang = RU
            }
            else -> {
                this.lang = EN
                options = appWordRepository.getReversedTranslateOptions() +
                        userWordRepository.getReversedTranslateOptions()
            }
        }
        updateScoring()
        tryToInitScoreBlessing()
        tryToInitContestBlessing()
    }

    private fun updateScoring() {
        val score = scoreRepository.getScoreFor(lang)
        scoreOnRight = getScoreOnRightBy(score)
        scoreOnWrong = getScoreOnWrongBy(score)
        coinsOnRight = getCoinsOnRightBy(score)
    }

    fun updateContest(count: Int = MAX_OPTIONS) {
        if (currentWordIndex < options.size && currentWordIndex < maxContestUnits) {
            tryToUpdateLangOnMysteryBlessing()
            viewState.updateContest(getCurrentAnswer(), getOptionsForCurrentAnswer(count), this)
            isReliefEstablished = false
        } else {
            viewState.startActivity(MainActivity::class.java)
        }
        viewState.updateScore()
    }

    fun updateContestFor60Percent(isFree: Boolean = false) {
        if (!isReliefEstablished) {
            if (isFree || userRepository.spendCoins(percent60Cost)) {
                updateContest(3)
                isReliefEstablished = true
            } else {
                viewState.showToastIfCannotPurchase()
            }
        }
    }

    fun updateContestFor40Percent() {
        if (!isReliefEstablished) {
            if (userRepository.spendCoins(percent40Cost)) {
                updateContest(2)
                isReliefEstablished = true
            } else {
                viewState.showToastIfCannotPurchase()
            }
        }
    }

    private fun getCurrentAnswer(): String =
        options[currentWordIndex].word

    private fun getOptionsForCurrentAnswer(count: Int) : List<TranslateOption> {
        val currentOptions = mutableListOf(options[currentWordIndex])
        while (currentOptions.size < count) {
            val index = Random.nextInt(0, options.size - 1)
            if (options[index] !in currentOptions)
                currentOptions.add(options[index])
        }
        return currentOptions.shuffled()
    }

    // TranslateAdapter.Listener implementation
    override fun onRightAnswer() {
        rightAnswersInRow++
        viewState.hidePrevRightAnswer()
        scoreRepository.updateScore(scoreOnRight, lang)
        if (blessingRepository.getBlessing() == BlessingHelper.Blessings.COINS) {
            val optional = if (Random.nextInt(1, 4) == 1) 1 else 0
            userRepository.addCoins(coinsOnRight + optional)
        } else {
            userRepository.addCoins(coinsOnRight)
        }
        currentWordIndex++
        if (tryToUpdateContestOnLuckBlessing())
            updateContestFor60Percent(true)
        else
            updateContest()
        tryToUnlockScoreBlessing()
        tryToUnlockCoinsBlessing()
        tryToUnlockContestBlessing()
        updateScoring()
    }

    override fun onWrongAnswer() {
        rightAnswersInRow = 0
        if (tryToQuitIfLostOnContestBlessing())
            return
        val prevOption = options[currentWordIndex]
        viewState.showPrevRightAnswer(prevOption)
        usersWrongOptions.add(prevOption)
        scoreRepository.updateScore(scoreOnWrong, lang)
        tryToLostCoinOnCoinsBlessing()
        currentWordIndex++
        if (tryToUpdateContestOnLuckBlessing())
            updateContestFor60Percent(true)
        else
            updateContest()
        updateScoring()
    }
}