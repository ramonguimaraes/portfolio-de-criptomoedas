package com.ramongapolinario.portfoliodecriptomoedas.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ramongapolinario.portfoliodecriptomoedas.R
import com.ramongapolinario.portfoliodecriptomoedas.db.CoinDAO
import com.ramongapolinario.portfoliodecriptomoedas.db.RegisterDAO
import com.ramongapolinario.portfoliodecriptomoedas.model.Coin
import com.ramongapolinario.portfoliodecriptomoedas.model.Register
import kotlinx.android.synthetic.main.fragment_add.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //auto-complete
        val coins = resources.getStringArray(R.array.coins)
        val arrayAdapter = ArrayAdapter(activity!!.applicationContext, android.R.layout.simple_list_item_1, coins)

        ac_coin.threshold = 0
        ac_coin.setAdapter(arrayAdapter)


        btnAddCoin.setOnClickListener {

            val context = activity!!.applicationContext

            val coinDao = CoinDAO(context)
            val registerDao = RegisterDAO(context)

            val timeNow = getTime()!!.split(" ")
            val date = timeNow[0]
            val hour = timeNow[1]

            val coinName =  ac_coin.text.toString()
            val coinInitials = ac_coin.text.take(3).toString()
            val coinAmount = edtAmount.text.toString().toDouble()
            val coinPurchasePrice = edtPrice.text.toString().toDouble()

            val coin = Coin(null, coinName, coinInitials, coinAmount, coinPurchasePrice)


            val id = coinDao.insert(coin)
            val register = Register(null, id.toInt(), date, hour)
            val r = registerDao.insert(register)
            Log.e("R", r)

            Toast.makeText(activity!!.applicationContext, "Data atual: $date", Toast.LENGTH_SHORT).show()
        }


    }


    fun getTime(): String? {
        val date = LocalDateTime.now()

        val formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        val formatedTime = date.format(formater)

        return formatedTime

    }
}