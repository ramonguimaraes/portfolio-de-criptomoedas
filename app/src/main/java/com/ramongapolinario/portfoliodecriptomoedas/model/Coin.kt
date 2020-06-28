package com.ramongapolinario.portfoliodecriptomoedas.model

import com.ramongapolinario.portfoliodecriptomoedas.requests.HttpRequester

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

    fun lastValue(): Double? {
        val httpReq = HttpRequester()
        return httpReq.getLastValue(this.initials)
    }
}