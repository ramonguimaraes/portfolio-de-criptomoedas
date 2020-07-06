package com.ramongapolinario.portfoliodecriptomoedas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramongapolinario.portfoliodecriptomoedas.R
import com.ramongapolinario.portfoliodecriptomoedas.db.CoinDAO
import com.ramongapolinario.portfoliodecriptomoedas.model.Coin
import com.ramongapolinario.portfoliodecriptomoedas.model.Register
import kotlinx.android.synthetic.main.history_item_layout.view.*

class RegisterAdapter(val registers: ArrayList<Register>?) : RecyclerView.Adapter<RegisterAdapter.VH>() {

    var ctx: Context? = null

    class VH (itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(register: Register, coin: Coin) {
            itemView.txtHCoin.text = coin.name
            itemView.txtHDate.text = register.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item_layout, parent, false)
        val vh = VH(view)

        ctx = parent.context

        return vh
    }

    override fun getItemCount(): Int {
        return registers!!.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(registers!![position], getCoin(registers[position].coinId))
    }

    fun getCoin(id: Int): Coin {

        val coinDao = CoinDAO(ctx!!)
        val coins = coinDao.getById(id)
        return coins[0]
    }

}