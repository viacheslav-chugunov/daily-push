package com.dev.dailypush.app.presentation.addition

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dev.dailypush.R
import com.dev.dailypush.app.base.App
import com.dev.dailypush.app.base.AppActivity
import com.dev.dailypush.app.presentation.main.MainActivity
import com.dev.dailypush.business.extensions.bindToolbar
import com.dev.dailypush.business.extensions.showToast
import com.dev.dailypush.business.extensions.startActivityInNewTask
import com.dev.dailypush.data.db.helper.BlessingHelper.Blessings.LUCK
import com.dev.dailypush.data.repository.AppWordRepository
import com.dev.dailypush.data.repository.BlessingRepository
import com.dev.dailypush.data.repository.UserRepository
import com.dev.dailypush.data.repository.UserWordRepository
import kotlinx.android.synthetic.main.activity_word_addition.*
import javax.inject.Inject

class WordAdditionActivity : AppActivity(R.layout.activity_word_addition) {

    @Inject lateinit var appWordRepository: AppWordRepository
    @Inject lateinit var userWordRepository: UserWordRepository
    @Inject lateinit var userRepository: UserRepository
    @Inject lateinit var blessingRepository: BlessingRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackground(bg_container)
    }


    override fun setupToolbar() =
        bindToolbar(getString(R.string.addition_toolbar_title), true)

    override fun injectDagger() =
        App.appComponent.inject(this)

    fun onClickAppendButton(view: View) {
        val word = ru_word.text.toString().trim()
        val translate = en_word.text.toString().trim()
        if (word.isNotBlank() && translate.isNotBlank()) {
            if (appWordRepository.contains(word, translate) || userWordRepository.contains(word, translate)) {
                showToast(R.string.word_is_already_exists, Toast.LENGTH_SHORT)
            } else {
                userWordRepository.appendTranslateOption(word, translate)
                userRepository.addCoins(3)
                tryToUnlockLuckBlessing()
            }
            ru_word.setText("")
            en_word.setText("")
        }
    }

    private fun tryToUnlockLuckBlessing() {
        if (!blessingRepository.isBlessingUnlocked(LUCK) && userWordRepository.getTranslateOptions().count() >= 50) {
            blessingRepository.unlockBlessing(LUCK)
            showToast(R.string.luck_blessing_unlocked, Toast.LENGTH_SHORT)
        }
    }

    fun onClickRemoveButton(view: View) {
        val wordsCount = userWordRepository.getTranslateOptions().count()
        val cost = 4 * wordsCount
        if (wordsCount > 0) {
            if (userRepository.spendCoins(cost)) {
                userWordRepository.removeAll()
                showToast(R.string.word_was_removed, Toast.LENGTH_SHORT)
            } else {
                val msg = "${getString(R.string.have_no_coins)} $cost"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() =
        startActivityInNewTask(MainActivity::class.java)

}