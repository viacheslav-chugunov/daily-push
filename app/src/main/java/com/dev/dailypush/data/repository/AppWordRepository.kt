package com.dev.dailypush.data.repository

import android.content.Context
import com.dev.dailypush.business.consts.Lang.EN
import com.dev.dailypush.business.consts.Lang.RU
import com.dev.dailypush.business.models.TranslateOption
import com.dev.dailypush.data.db.helper.AppWordHelper
import com.dev.dailypush.data.repository.abs.WordRepository

class AppWordRepository(c: Context) : WordRepository(c, AppWordHelper.TABLE) {

    fun getTranslateOptions() : List<TranslateOption> =
        getTranslateOptionsFor(AppWordHelper(context))

    fun getReversedTranslateOptions() : List<TranslateOption> =
        getReversedTranslateOptionsFor(AppWordHelper(context))

    fun contains(ruWord: String, enTranslate: String) : Boolean {
        for (option in getTranslateOptions()) {
            if (ruWord.toUpperCase() == option.word || enTranslate.toUpperCase() == option.translate) {
                return true
            }
        }
        return false
    }
}