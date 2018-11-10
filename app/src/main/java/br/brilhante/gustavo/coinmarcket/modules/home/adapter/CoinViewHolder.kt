package br.brilhante.gustavo.coinmarcket.modules.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.brilhante.gustavo.coinmarcket.R
import br.brilhante.gustavo.coinmarcket.extensions.inflate
import br.brilhante.gustavo.coinmarcket.models.Coin
import kotlinx.android.synthetic.main.item_home.view.*

class CoinViewHolder(val parent: ViewGroup, private val listener: CoinListener) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.item_home, false)) {

    fun bind(coin: Coin) {
        itemView.nameTextView.text =
                String.format(itemView.context.getString(R.string.name_coin), coin.name, coin.symbol)

        itemView.marketCapTextView.text = String.format(itemView.context.getString(R.string.market_cap),
            coin.quotes?.uSD?.marketCap ?: kotlin.run { itemView.context.getString(R.string.not_found) })

        itemView.priceTextView.text = String.format(itemView.context.getString(R.string.price),
            coin.quotes?.uSD?.price ?: kotlin.run { itemView.context.getString(R.string.not_found) })

        itemView.setOnClickListener {
            listener.onCoinClick(coin)
        }
    }

}