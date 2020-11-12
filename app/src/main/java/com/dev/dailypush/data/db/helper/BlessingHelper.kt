package com.dev.dailypush.data.db.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BlessingHelper(c: Context) : SQLiteOpenHelper(c, TABLE, null, VERSION) {

    companion object {
        const val TABLE = "blessing_table"
        const val VERSION = 3
    }

    object Key {
        const val BLESSING = "blessing"
        const val IS_AVAILABLE = "is_available"
        const val IS_ENABLED = "is_enabled"
    }

    object Blessings {
        const val NONE = "none"
        const val SCORE = "score"
        const val COINS = "coins"
        const val CONTEST = "contest"
        const val LUCK = "luck"
        const val MYSTERY = "mystery"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table ${TABLE}(${Key.BLESSING} text primary key," +
                " ${Key.IS_ENABLED} integer," +
                " ${Key.IS_AVAILABLE} integer);")
        insertBlessing(db, Blessings.NONE, true)
        insertBlessing(db, Blessings.SCORE, false)
        insertBlessing(db, Blessings.COINS, false)
        insertBlessing(db, Blessings.CONTEST, false)
        insertBlessing(db, Blessings.LUCK, false)
        insertBlessing(db, Blessings.MYSTERY, false)

    }

    private fun insertBlessing(db: SQLiteDatabase, blessing: String, isAvailable: Boolean) {
        val content = ContentValues()
        content.put(Key.BLESSING, blessing)
        content.put(Key.IS_AVAILABLE, if (isAvailable) 1 else 0)
        content.put(Key.IS_ENABLED, 0)
        db.insert(TABLE, null, content)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 1) {
            insertBlessing(db, Blessings.LUCK, false)
            insertBlessing(db, Blessings.MYSTERY, false)
        } else if (oldVersion == 2) {
            insertBlessing(db, Blessings.MYSTERY, false)
        } else {
            db.execSQL("drop table if exists $TABLE")
            onCreate(db)
        }
    }
}