package com.ramongapolinario.portfoliodecriptomoedas.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.ramongapolinario.portfoliodecriptomoedas.db.*
import com.ramongapolinario.portfoliodecriptomoedas.requests.HttpRequester
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Coin (

    val id: Int?,
    val name: String,
    val initials: String,
    val amount: Double, 
    val purchasePrice: Double //valor da cotaçao no ato da compra

) {

    fun calcVariation(lastValue: Double): Double {
        val variation = lastValue - purchasePrice
        return (variation/purchasePrice)*100
    }

    fun calcAmountValue(lastValue: Double): Double {
        return lastValue * this.amount
    }

}