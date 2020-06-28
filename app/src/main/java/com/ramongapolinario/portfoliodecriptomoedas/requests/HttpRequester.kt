package com.ramongapolinario.portfoliodecriptomoedas.requests

import android.content.Context
import android.net.ConnectivityManager
import com.ramongapolinario.portfoliodecriptomoedas.requests.json.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.concurrent.TimeUnit


class HttpRequester {

    fun hasConnection(ctx: Context): Boolean{

        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info != null && info.isConnected

    }

    fun getLastValue(coin: String): Double? {

        val url = "https://www.mercadobitcoin.net/api/${coin}/ticker/"

        val client = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url(url).build()

        val response = client.newCall(request).execute()

        val jsonString = response.body?.string()

        val json = JSONObject(jsonString)

        return JsonParser.readLast(json)
    }

}

