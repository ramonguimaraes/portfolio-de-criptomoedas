package com.ramongapolinario.portfoliodecriptomoedas.db

 
 
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.ramongapolinario.portfoliodecriptomoedas.model.Register

class RegisterDAO(context: Context) {
    var banco = DBHelper(context)

    fun insert(register: Register): String {
        val db = banco.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COIN, register.coinId)
        contentValues.put(DATE, register.date)
        contentValues.put(HOUR, register.hour)

        val resp_id = db.insert(TABLE_NAME_REGISTER, null, contentValues)

        val msg = if(resp_id != -1L){
            "Inserido com sucesso"
        }else{
            "Erro ao inserir"
        }

        db.close()
        return msg
    }


    fun update(register: Register): Boolean {
        val db = banco.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(REGISTER_ID, register.id)
        contentValues.put(COIN, register.coinId)
        contentValues.put(DATE, register.date)
        contentValues.put(HOUR, register.hour)

        db.update(TABLE_NAME_REGISTER, contentValues, "ID = ?", arrayOf(register.id.toString()))

        db.close()
        return true
    }


    fun remove(register: Register) : Int {
        val db = banco.writableDatabase
        return db.delete(TABLE_NAME_REGISTER,"ID = ?", arrayOf(register.id.toString()))
    }


    fun getAll(): ArrayList<Register>{

        val db = banco.writableDatabase
        val  sql = "SELECT *  from " + TABLE_NAME_REGISTER
        val cursor = db.rawQuery(sql ,null)
        val registers = ArrayList<Register>()

        while (cursor.moveToNext()){
            val register = registerFromCursor(cursor)
            registers.add(register)
        }

        cursor.close()
        db.close()

        return registers
    }


    fun getByName(name:String): ArrayList<Register>{

        val db = banco.writableDatabase
        val sql = "SELECT * FROM $TABLE_NAME WREGISTER $COIN_NAME LIKE $name;"
        val cursor = db.rawQuery(sql ,null)
        val registers = ArrayList<Register>()

        while (cursor.moveToNext()){
            registers.add(registerFromCursor(cursor))
        }

        cursor.close()
        db.close()

        return registers
    }

    private fun registerFromCursor(cursor: Cursor): Register{
        val id = cursor.getInt(cursor.getColumnIndex(REGISTER_ID))
        val coin = cursor.getInt(cursor.getColumnIndex(COIN))
        val date = cursor.getString(cursor.getColumnIndex(DATE))
        val hour = cursor.getString(cursor.getColumnIndex(HOUR))

        return Register(id, coin, date, hour)
    }
}
