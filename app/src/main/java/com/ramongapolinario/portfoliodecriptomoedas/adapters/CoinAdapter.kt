package com.ramongapolinario.portfoliodecriptomoedas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramongapolinario.portfoliodecriptomoedas.R
import com.ramongapolinario.portfoliodecriptomoedas.model.Coin
import kotlinx.android.synthetic.main.item_layout.view.*

class CoinAdapter(val coins: ArrayList<Coin>?) : RecyclerView.Adapter<CoinAdapter.VH>() {
    class VH (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(coin: Coin) {
            itemView.txtCoin.text = coin.initials
            itemView.txtCoinAmount.text = String.format("%.6f", coin.amount)
            itemView.txtAmountPrice.text = (coin.amount * coin.value).toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        val vh = VH(view)

        return vh
    }

    override fun getItemCount(): Int {
        return coins!!.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(coins!![position])
    }


}