package com.dev.dailypush.data.db.helper.abs

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dev.dailypush.business.consts.Lang
import com.dev.dailypush.data.db.initter.WordInitter

abstract class WordHelper(c: Context, private val table: String, version: Int) : SQLiteOpenHelper(c, table, null, version) {

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists $table")
        onCreate(db)
    }
}