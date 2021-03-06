package com.ramongapolinario.portfoliodecriptomoedas.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val coinTableSQL = "" +
                "CREATE TABLE $TABLE_NAME (" +
                "$COIN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COIN_NAME VARCHAR(50) NOT NULL, " +
                "$COIN_INITIALS VARCHAR(50) NOT NULL, " +
                "$COIN_AMOUNT DOUBLE NOT NULL, " +
                "$COIN_ATUAL_PRICE DOUBLE NOT NULL, " +
                "$COIN_PRICE DOUBLE NOT NULL); "

        val registerTableSQL = "" +
                "CREATE TABLE $TABLE_NAME_REGISTER (" +
                "$REGISTER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COIN INTEGER NOT NULL, " +
                "$DATE VARCHAR(10) NOT NULL, " +
                "$HOUR VARCHAR(10) NOT NULL); "
//                "FOREIGN KEY($COIN) REFERENCES $TABLE_NAME($COIN_ID));"

        val dayTableSQL = "" +
                "CREATE TABLE $TABLE_DAY_TOTAL (" +
                "$DAY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$TOTAL DOUBLE, " +
                "$DAY_DATE VARCHAR(15));"

        db.execSQL(coinTableSQL)
        db.execSQL(registerTableSQL)
        db.execSQL(dayTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_REGISTER")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_DAY_TOTAL")
        onCreate(db)
    }
}