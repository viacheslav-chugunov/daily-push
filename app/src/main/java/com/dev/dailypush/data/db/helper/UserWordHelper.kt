package com.dev.dailypush.data.db.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dev.dailypush.business.consts.Lang
import com.dev.dailypush.data.db.helper.abs.WordHelper
import com.dev.dailypush.data.db.initter.WordInitter

class UserWordHelper(c: Context) : WordHelper(c, TABLE, VERSION) {
    companion object {
        const val TABLE = "user_word_table"
        const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table ${TABLE}(${Lang.RU} text primary key, ${Lang.EN} text);")
    }
}