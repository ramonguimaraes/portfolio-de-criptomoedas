package com.ramongapolinario.portfoliodecriptomoedas.requests.json

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

object JsonParser {

    fun readLast(jsonObject: JSONObject): Double? {
        var last: Double? = null
        var ticker: JSONObject? = null
        try {
            ticker = jsonObject.getJSONObject("ticker")
            last = ticker.getDouble("last")

            Log.e("Preco da moeda: ", "${last}")

        } catch (e: JSONException) {

            Log.e("Erro:", "${e}")

        }

        return last
    }
}