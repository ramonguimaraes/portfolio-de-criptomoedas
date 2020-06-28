package com.ramongapolinario.portfoliodecriptomoedas.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.ramongapolinario.portfoliodecriptomoedas.model.Coin

class CoinDAO(context: Context) {
    var banco = DBHelper(context)

    fun insert(coin: Coin): Long {
        val db = banco.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COIN_NAME, coin.name)
        contentValues.put(COIN_INITIALS, coin.initials)
        contentValues.put(COIN_AMOUNT, coin.amount)
        contentValues.put(COIN_PRICE, coin.purchasePrice)

        val resp_id = db.insert(TABLE_NAME, null, contentValues)



        db.close()
        return resp_id
    }


    fun update(coin: Coin): Boolean {
        val db = banco.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COIN_ID, coin.id)
        contentValues.put(COIN_NAME, coin.name)
        contentValues.put(COIN_AMOUNT, coin.amount)
        contentValues.put(COIN_PRICE, coin.purchasePrice)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(coin.id.toString()))

        db.close()
        return true
    }


    fun remove(coin: Coin) : Int {
        val db = banco.writableDatabase
        return db.delete(TABLE_NAME,"ID = ?", arrayOf(coin.id.toString()))
    }


    fun getAll(): ArrayList<Coin>{

        val db = banco.writableDatabase
        val  sql = "SELECT *  from " + TABLE_NAME
        val cursor = db.rawQuery(sql ,null)
        val coins = ArrayList<Coin>()

        while (cursor.moveToNext()){
            val coin= coinFromCursor(cursor)
            coins.add(coin)
        }

        cursor.close()
        db.close()

        return coins
    }

    fun getById(id: Int ): ArrayList<Coin>{

        val db = banco.writableDatabase
        val sql = "SELECT * FROM $TABLE_NAME WHERE $COIN_ID LIKE $id;"
        val cursor = db.rawQuery(sql ,null)
        val coins = ArrayList<Coin>()

        while (cursor.moveToNext()){
            coins.add(coinFromCursor(cursor))
        }

        cursor.close()
        db.close()

        return coins
    }


//    fun getById(id: Int): Coin{
//
//        val db = banco.writableDatabase
//        val sql = "SELECT * FROM $TABLE_NAME WHERE $COIN_ID LIKE $id;"
//        val cursor = db.rawQuery(sql,null)
//
//        val coin = coinFromCursor(cursor)
//
//        cursor.close()
//        db.close()
//
//        return coin
//    }

    private fun coinFromCursor(cursor: Cursor): Coin{
        val id = cursor.getInt(cursor.getColumnIndex(COIN_ID))
        val name = cursor.getString(cursor.getColumnIndex(COIN_NAME))
        val initials = cursor.getString(cursor.getColumnIndex(COIN_INITIALS))
        val amount = cursor.getDouble(cursor.getColumnIndex(COIN_AMOUNT))
        val purchasePrice = cursor.getDouble(cursor.getColumnIndex(COIN_PRICE))
        return Coin(id, name, initials, amount, purchasePrice)
    }
}
