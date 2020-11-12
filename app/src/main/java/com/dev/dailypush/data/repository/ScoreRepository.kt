package com.dev.dailypush.data.repository

import android.content.ContentValues
import android.content.Context
import com.dev.dailypush.business.consts.Lang
import com.dev.dailypush.data.db.helper.ScoreHelper
import com.dev.dailypush.data.repository.abs.Repository

class ScoreRepository(c: Context) : Repository(c, ScoreHelper.TABLE) {

    fun getScore() : Int =
        getScoreFor(Lang.RU) +  getScoreFor(Lang.EN)

    fun getScoreFor(lang: String) : Int {
        val db = ScoreHelper(context).readableDatabase
        var score = 0
        val cursor = query(db)
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(ScoreHelper.Key.TO_LANG)) == lang)
                    score += cursor.getInt(cursor.getColumnIndex(ScoreHelper.Key.SCORE))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return score
    }

    fun updateScore(score: Int, lang: String) {
        var prevScore = 0
        val db = ScoreHelper(context).writableDatabase
        val cursor = query(db)
        if (cursor.moveToFirst()) {
            do {
                if (lang == cursor.getString(cursor.getColumnIndex(ScoreHelper.Key.TO_LANG)))
                    prevScore += cursor.getInt(cursor.getColumnIndex(ScoreHelper.Key.SCORE))
            } while (cursor.moveToNext())
            cursor.close()
        }
        val content = ContentValues()
        val newScore = prevScore + score
        if (newScore > 0)
            content.put(ScoreHelper.Key.SCORE, newScore)
        else
            content.put(ScoreHelper.Key.SCORE, 0)
        db.update(ScoreHelper.TABLE, content, "${ScoreHelper.Key.TO_LANG} = ?", arrayOf(lang))
        db.close()
    }
}