package br.brilhante.gustavo.coinmarcket.modules.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.brilhante.gustavo.coinmarcket.database.coin.CoinRepository
import br.brilhante.gustavo.coinmarcket.models.Coin

class DetailViewModel : ViewModel() {

    var coinLiveData: LiveData<Coin>? = null

    fun setupCoinId(coinId: Int) {
        coinLiveData = CoinRepository.getCoinLiveDataById(coinId)
    }
}