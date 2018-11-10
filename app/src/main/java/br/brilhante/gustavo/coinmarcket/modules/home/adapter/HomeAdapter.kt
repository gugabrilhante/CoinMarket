package br.brilhante.gustavo.coinmarcket.modules.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.brilhante.gustavo.coinmarcket.models.Coin

class HomeAdapter(private val listener: CoinListener) : RecyclerView.Adapter<CoinViewHolder>() {

    var coinList: List<Coin> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder = CoinViewHolder(parent, listener)

    override fun getItemCount(): Int = coinList.size

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coinList[position])
    }
}