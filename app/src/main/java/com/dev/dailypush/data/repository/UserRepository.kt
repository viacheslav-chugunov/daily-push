package com.dev.dailypush.data.repository

import android.content.ContentValues
import android.content.Context
import com.dev.dailypush.data.db.helper.UserHelper
import com.dev.dailypush.data.repository.abs.Repository

class UserRepository(c: Context) : Repository(c, UserHelper.TABLE) {

    fun getCoinsCount() : Int =
        getArgOfValue(UserHelper.Value.COINS_COUNT)

    fun getTicketsCount() : Int =
        getArgOfValue(UserHelper.Value.TICKETS_COUNT)


    fun spendTicket() : Boolean {
        val ticketsCount = getTicketsCount()
        if (ticketsCount > 0) {
            val db = UserHelper(context).writableDatabase
            val content = ContentValues()
            content.put(UserHelper.Key.ARG, ticketsCount - 1)
            db.update(UserHelper.TABLE, content, "${UserHelper.Key.VALUE} = ?",
                arrayOf(UserHelper.Value.TICKETS_COUNT))
            return true
        }
        return false
    }

    fun spendCoins(count: Int) : Boolean {
        val coins = getCoinsCount()
        if (coins - count > 0) {
            val db = UserHelper(context).writableDatabase
            val content = ContentValues()
            content.put(UserHelper.Key.ARG, coins - count)
            db.update(UserHelper.TABLE, content, "${UserHelper.Key.VALUE} = ?",
                arrayOf(UserHelper.Value.COINS_COUNT))
            db.close()
            return true
        }
        return false
    }

    fun addTickets(count: Int) {
        val tickets = getTicketsCount()
        val db = UserHelper(context).writableDatabase
        val content = ContentValues()
        content.put(UserHelper.Key.ARG, tickets + count)
        db.update(UserHelper.TABLE, content, "${UserHelper.Key.VALUE} = ?",
            arrayOf(UserHelper.Value.TICKETS_COUNT))
        db.close()
    }

    fun addCoins(count: Int) {
        spendCoins(-count)
    }

    fun getTheme() : Int =
        getArgOfValue(UserHelper.Value.THEME)


    fun setTheme(res: Int) {
        val db = UserHelper(context).writableDatabase
        val content = ContentValues()
        content.put(UserHelper.Key.ARG, res)
        db.update(UserHelper.TABLE, content, "${UserHelper.Key.VALUE} = ?",
            arrayOf(UserHelper.Value.THEME))
        db.close()
    }

    private fun getArgOfValue(value: String) : Int {
        var arg = 0
        val db = UserHelper(context).readableDatabase
        val cursor = query(db)
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(UserHelper.Key.VALUE)) == value)
                    arg = cursor.getInt(cursor.getColumnIndex(UserHelper.Key.ARG))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return arg
    }
}