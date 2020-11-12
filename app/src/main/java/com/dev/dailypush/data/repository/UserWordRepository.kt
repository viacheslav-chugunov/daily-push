package com.dev.dailypush.data.repository

import android.content.ContentValues
import android.content.Context
import com.dev.dailypush.business.consts.Lang
import com.dev.dailypush.business.models.TranslateOption
import com.dev.dailypush.data.db.helper.AppWordHelper
import com.dev.dailypush.data.db.helper.UserHelper
import com.dev.dailypush.data.db.helper.UserWordHelper
import com.dev.dailypush.data.db.helper.abs.WordHelper
import com.dev.dailypush.data.repository.abs.WordRepository

class UserWordRepository(c: Context) : WordRepository(c, UserWordHelper.TABLE) {

    fun getTranslateOptions(): List<TranslateOption> =
        getTranslateOptionsFor(UserWordHelper(context))

    fun getReversedTranslateOptions(): List<TranslateOption> =
        getReversedTranslateOptionsFor(UserWordHelper(context))

    fun appendTranslateOption(ru: String, en: String) {
        val db = UserWordHelper(context).writableDatabase
        val content = ContentValues()
        content.put(Lang.RU, ru.toUpperCase())
        content.put(Lang.EN, en.toUpperCase())
        db.insert(table, null, content)
        db.close()
    }

    fun removeAll() {
        val db = UserWordHelper(context).writableDatabase
        db.delete(table, null, null)
        db.close()
    }

    fun contains(ruWord: String, enTranslate: String) : Boolean {
        for (option in getTranslateOptions()) {
            if (ruWord.toUpperCase() == option.word || enTranslate.toUpperCase() == option.translate) {
                return true
            }
        }
        return false
    }
}