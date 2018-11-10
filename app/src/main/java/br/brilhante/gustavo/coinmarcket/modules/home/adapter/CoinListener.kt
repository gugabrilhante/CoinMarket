package br.brilhante.gustavo.coinmarcket.modules.home.adapter

import br.brilhante.gustavo.coinmarcket.models.Coin

interface CoinListener {
    fun onCoinClick(coin: Coin)
}