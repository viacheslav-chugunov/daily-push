package com.dev.dailypush.data.repository

import android.content.ContentValues
import android.content.Context
import com.dev.dailypush.data.db.helper.BlessingHelper
import com.dev.dailypush.data.repository.abs.Repository

class BlessingRepository(c: Context) : Repository(c, BlessingHelper.TABLE) {

    fun getBlessing() : String {
        var blessing = BlessingHelper.Blessings.NONE
        val db = BlessingHelper(context).readableDatabase
        val cursor = query(db)
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(cursor.getColumnIndex(BlessingHelper.Key.IS_ENABLED)) == 1) {
                    blessing = cursor.getString(cursor.getColumnIndex(BlessingHelper.Key.BLESSING))
                    break
                }
            } while (cursor.moveToNext())
            cursor.close()
        }
        db.close()
        return blessing
    }

    fun setBlessing(blessing: String) {
        val db = BlessingHelper(context).writableDatabase
        var content = ContentValues()
        content.put(BlessingHelper.Key.IS_ENABLED, 0)
        db.update(BlessingHelper.TABLE, content, "${BlessingHelper.Key.BLESSING} = ?",
            arrayOf(BlessingHelper.Blessings.SCORE))
        db.update(BlessingHelper.TABLE, content, "${BlessingHelper.Key.BLESSING} = ?",
            arrayOf(BlessingHelper.Blessings.COINS))
        db.update(BlessingHelper.TABLE, content, "${BlessingHelper.Key.BLESSING} = ?",
            arrayOf(BlessingHelper.Blessings.CONTEST))
        db.update(BlessingHelper.TABLE, content, "${BlessingHelper.Key.BLESSING} = ?",
            arrayOf(BlessingHelper.Blessings.NONE))
        db.update(BlessingHelper.TABLE, content, "${BlessingHelper.Key.BLESSING} = ?",
            arrayOf(BlessingHelper.Blessings.LUCK))
        db.update(BlessingHelper.TABLE, content, "${BlessingHelper.Key.BLESSING} = ?",
            arrayOf(BlessingHelper.Blessings.MYSTERY))
        content = ContentValues()
        content.put(BlessingHelper.Key.BLESSING, blessing)
        content.put(BlessingHelper.Key.IS_ENABLED, 1)
        db.update(BlessingHelper.TABLE, content, "${BlessingHelper.Key.BLESSING} = ?",
            arrayOf(blessing))
        db.close()
    }

    fun isBlessingUnlocked(blessing: String) : Boolean {
        var isUnlocked = 0
        val db = BlessingHelper(context).writableDatabase
        val cursor = query(db)
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(BlessingHelper.Key.BLESSING)) == blessing) {
                    isUnlocked = cursor.getInt(cursor.getColumnIndex(BlessingHelper.Key.IS_AVAILABLE))
                    break
                }
            } while (cursor.moveToNext())
            cursor.close()
        }
        db.close()
        return isUnlocked == 1
    }

    fun unlockBlessing(blessing: String) {
        val db = BlessingHelper(context).writableDatabase
        val content = ContentValues()
        content.put(BlessingHelper.Key.IS_AVAILABLE, 1)
        db.update(BlessingHelper.TABLE, content, "${BlessingHelper.Key.BLESSING} = ?",
            arrayOf(blessing))
        db.close()
    }
}