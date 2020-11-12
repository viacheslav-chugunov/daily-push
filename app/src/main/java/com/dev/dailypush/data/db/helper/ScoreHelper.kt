package com.dev.dailypush.data.db.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dev.dailypush.data.db.initter.ScoreInitter

class ScoreHelper(c: Context) : SQLiteOpenHelper(c, TABLE, null, VERSION) {

    companion object {
        const val TABLE = "score_table"
        const val VERSION = 1
    }

    object Key {
        const val TO_LANG = "to_lang"
        const val SCORE = "score"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE(${Key.TO_LANG} text primary key, ${Key.SCORE} integer);")
        ScoreInitter.init(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists $TABLE")
        onCreate(db)
    }
}