package com.ramongapolinario.portfoliodecriptomoedas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramongapolinario.portfoliodecriptomoedas.R
import com.ramongapolinario.portfoliodecriptomoedas.adapters.CoinAdapter
import com.ramongapolinario.portfoliodecriptomoedas.model.Coin
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lst: ArrayList<Coin>? = arrayListOf(
            //lista de testes
            Coin(null, "Bitcoin", "BTC", 0.1001, 45000.00),
            Coin(null, "Bitcoin", "BTC", 0.1000, 45000.00),
            Coin(null, "Bitcoin", "BTC", 1.0, 47000.00)
        )

        val context = activity!!.applicationContext
        val adapter = CoinAdapter(lst)

        rv_list.adapter = adapter
        rv_list.layoutManager = LinearLayoutManager(context)

    }
}
