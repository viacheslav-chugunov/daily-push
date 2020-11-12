package com.dev.dailypush.data.db.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dev.dailypush.R

class UserHelper(c: Context) : SQLiteOpenHelper(c, TABLE, null, VERSION) {

    companion object {
        const val TABLE = "user_table"
        const val VERSION = 2
    }

    object Key {
        const val VALUE = "value"
        const val ARG = "arg"
    }

    object Value {
        const val COINS_COUNT = "coins_count"
        const val TICKETS_COUNT = "tickets_count"
        const val THEME = "theme"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table ${TABLE}(${Key.VALUE} text primary key, " +
                "${Key.ARG} text);")
        insertValue(db, Value.COINS_COUNT, 100)
        insertValue(db, Value.TICKETS_COUNT, 5)
        insertValue(db, Value.THEME, R.style.BaseTheme)
    }

    private fun insertValue(db: SQLiteDatabase, value: String, arg: Int) {
        val content = ContentValues()
        content.put(Key.VALUE, value)
        content.put(Key.ARG, arg)
        db.insert(TABLE, null, content)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists $TABLE")
        onCreate(db)
    }
}