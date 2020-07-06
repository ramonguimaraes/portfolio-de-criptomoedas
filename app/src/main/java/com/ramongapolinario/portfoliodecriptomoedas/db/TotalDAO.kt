package com.ramongapolinario.portfoliodecriptomoedas.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.ramongapolinario.portfoliodecriptomoedas.model.Total

class TotalDAO(context: Context) {
    var banco = DBHelper(context)

    fun insert(total: Total): Long {
        val db = banco.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(TOTAL, total.total)
        contentValues.put(DAY_DATE, total.date)

        val resp_id = db.insert(TABLE_DAY_TOTAL, null, contentValues)

        db.close()
        return resp_id
    }


    fun update(total: Total): Boolean {
        val db = banco.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(TOTAL, total.total)
        contentValues.put(DAY_DATE, total.date)

        db.update(TABLE_DAY_TOTAL, contentValues, "ID = ?", arrayOf(total.id.toString()))

        db.close()
        return true
    }

    fun getTotal(): ArrayList<Total>{

        val db = banco.writableDatabase
        val sql = "SELECT * FROM $TABLE_DAY_TOTAL;"
        val cursor = db.rawQuery(sql ,null)
        val total = ArrayList<Total>()

        while (cursor.moveToNext()){
            total.add(totalFromCursor(cursor))
        }

        cursor.close()
        db.close()

        return total
    }

    private fun totalFromCursor(cursor: Cursor): Total{
        val id = cursor.getInt(cursor.getColumnIndex(DAY_ID))
        val total = cursor.getDouble(cursor.getColumnIndex(TOTAL))
        val date = cursor.getString(cursor.getColumnIndex(DAY_DATE))

        return Total(id, total, date)
    }
}
