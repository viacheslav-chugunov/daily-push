package com.dev.dailypush.data.repository.abs

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

open class Repository(protected val context: Context, protected val table: String) {

    protected fun query(db: SQLiteDatabase): Cursor =
        db.query(table, null, null, null, null, null, null)
}