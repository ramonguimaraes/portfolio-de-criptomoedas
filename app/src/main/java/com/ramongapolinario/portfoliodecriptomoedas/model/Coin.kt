package com.ramongapolinario.portfoliodecriptomoedas.model

class Coin (

    val id: Int?,
    val name: String,
    val initials: String,
    val amount: Double,
    val purchasePrice: Double, //valor da cotaçao no ato da compra
    var lastValue: Double?
) {

    fun calcVariation(lastValue: Double): Double {
        val variation = lastValue - purchasePrice
        return (variation/purchasePrice)*100
    }

    fun calcAmountValue(lastValue: Double): Double {
        return lastValue * this.amount
    }

}