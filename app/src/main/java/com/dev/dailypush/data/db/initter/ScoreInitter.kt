package com.dev.dailypush.data.db.initter

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.dev.dailypush.business.consts.Lang.EN
import com.dev.dailypush.business.consts.Lang.RU
import com.dev.dailypush.data.db.helper.ScoreHelper

object ScoreInitter {

    fun init(db: SQLiteDatabase) {
        addLang(db, RU)
        addLang(db, EN)
    }

    private fun addLang(db: SQLiteDatabase, lang: String) {
        val content = ContentValues()
        content.put(ScoreHelper.Key.TO_LANG, lang)
        content.put(ScoreHelper.Key.SCORE, 0)
        db.insert(ScoreHelper.TABLE, null, content)
    }
}