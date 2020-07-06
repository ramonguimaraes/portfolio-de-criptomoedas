package com.ramongapolinario.portfoliodecriptomoedas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ramongapolinario.portfoliodecriptomoedas.R
import com.ramongapolinario.portfoliodecriptomoedas.model.Coin
import com.ramongapolinario.portfoliodecriptomoedas.requests.HttpRequester
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class CoinAdapter(val coins: ArrayList<Coin>?) : RecyclerView.Adapter<CoinAdapter.VH>() {

    var context: Context? = null

    inner class VH (itemView: View): RecyclerView.ViewHolder(itemView){


        fun bind(coin: Coin) {
            var last: Double?
            CoroutineScope(IO).launch {
                last = HttpRequester.getLastValue(coin.initials)
                CoroutineScope(Main).launch {
                    itemView.txtCoin.text = coin.initials
                    itemView.txtCoinAmount.text = String.format("%.6f", coin.amount)
                    itemView.txtAmountPrice.text = "R$ ".plus(String.format("%.2f", coin.calcAmountValue(last!!)))
                    itemView.txtCoinVariation.text = String.format("%.2f", coin.calcVariation(last!!)).plus("%")
                    if(coin.calcVariation(last!!) < 0){
                        itemView.icCoinVariantion.setImageDrawable(
                            ContextCompat.getDrawable(
                                context!!,
                                R.drawable.ic_trending_down_24
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        val vh = VH(view)
        context = parent.context
        return vh
    }

    override fun getItemCount(): Int {
        return coins!!.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(coins!![position])
    }

}