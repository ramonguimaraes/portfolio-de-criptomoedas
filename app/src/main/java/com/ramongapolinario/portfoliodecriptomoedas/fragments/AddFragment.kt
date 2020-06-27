package com.ramongapolinario.portfoliodecriptomoedas.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.ramongapolinario.portfoliodecriptomoedas.R
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coins = resources.getStringArray(R.array.coins)

        val arrayAdapter = ArrayAdapter(activity!!.applicationContext, android.R.layout.simple_list_item_1, coins)

        ac_coin.threshold = 0
        ac_coin.setAdapter(arrayAdapter)
    }
}