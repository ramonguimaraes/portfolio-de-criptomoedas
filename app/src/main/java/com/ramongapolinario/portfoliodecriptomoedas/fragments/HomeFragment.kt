package com.ramongapolinario.portfoliodecriptomoedas.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramongapolinario.portfoliodecriptomoedas.R
import com.ramongapolinario.portfoliodecriptomoedas.adapters.CoinAdapter
import com.ramongapolinario.portfoliodecriptomoedas.db.CoinDAO
import com.ramongapolinario.portfoliodecriptomoedas.db.TotalDAO
import com.ramongapolinario.portfoliodecriptomoedas.model.Coin
import com.ramongapolinario.portfoliodecriptomoedas.model.Total
import com.ramongapolinario.portfoliodecriptomoedas.requests.HttpRequester
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ctx = activity!!.applicationContext
        val coinDao = CoinDAO(ctx)
        var lst = coinDao.getAll()

        mountRecyclerView(lst, ctx)
        txtTotal.text = String.format("%.2f", updateTotal(lst, ctx))

        swipeLayout.setOnRefreshListener {
            lst = coinDao.getAll()
            updateLastValues(lst, ctx)
            txtTotal.text = String.format("%.2f", updateTotal(lst, ctx))
        }
    }


    fun updateLastValues(lst: ArrayList<Coin>, ctx: Context){

        val coinDAO = CoinDAO(ctx)

        CoroutineScope(IO).launch {
            for(i in 0 until lst.size) {
                lst[i].lastValue = getLastValue(lst[i].initials)!!
                CoroutineScope(Main).launch {
                    coinDAO.update(lst[i])
                    swipeLayout.isRefreshing = false;
                }
            }
        }
    }

    fun getLastValue(coinInitials: String): Double?{
        return HttpRequester.getLastValue(coinInitials)
    }

    fun mountRecyclerView(lst: ArrayList<Coin>, ctx: Context) {
        val adapter = CoinAdapter(lst)
        rv_list.adapter = adapter
        rv_list.layoutManager = LinearLayoutManager(ctx)
        adapter.notifyDataSetChanged()
    }

    fun updateTotal(lst: ArrayList<Coin>,  ctx: Context): Double {
        var aux = 0.0
        var value = 0.0
        val totalDao = TotalDAO(ctx)
        val date = getDate()

        for(i in 0 until lst.size){
            aux = (lst[i].lastValue!! * lst[i].amount)
            value += aux
        }

        if(totalDao.getTotal().isNullOrEmpty()){
            totalDao.insert(Total(null, value, date!!))
        }else{
            if(totalDao.getTotal()[0].date != getDate()){
                totalDao.update(Total(totalDao.getTotal()[0].id, value, date!!))
            }
        }

        return value
    }

    fun getDate(): String? {

        val date = LocalDateTime.now()
        val formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        var formatedTime = date.format(formater)

        formatedTime = formatedTime.take(10)

        return formatedTime

    }

}
