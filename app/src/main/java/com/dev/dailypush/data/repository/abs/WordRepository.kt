package com.dev.dailypush.data.repository.abs

import android.content.Context
import com.dev.dailypush.business.consts.Lang
import com.dev.dailypush.business.models.TranslateOption
import com.dev.dailypush.data.db.helper.abs.WordHelper

open class WordRepository(c: Context, table: String) : Repository(c, table) {

    protected fun getTranslateOptionsFor(helper: WordHelper): List<TranslateOption> {
        val db = helper.readableDatabase
        val options = mutableListOf<TranslateOption>()
        val cursor = query(db)
        if (cursor.moveToFirst()) {
            do {
                val word = cursor.getString(cursor.getColumnIndex(Lang.EN))
                val translate = cursor.getString(cursor.getColumnIndex(Lang.RU))
                options.add(TranslateOption(word, translate))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return options.shuffled()
    }

    fun getReversedTranslateOptionsFor(helper: WordHelper) : List<TranslateOption> {
        val options = getTranslateOptionsFor(helper)
        options.forEach { it.reverse() }
        return options
    }
}