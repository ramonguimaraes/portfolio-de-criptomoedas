package com.ramongapolinario.portfoliodecriptomoedas.requests.json

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

object JsonParser {

    fun readLast(jsonObject: JSONObject): Double? {
        var last: Double? = null

        try {

            last = jsonObject.getDouble("last")

        } catch (e: JSONException) {

            Log.e("Erro:", "${e}")

        }

        return last
    }
}