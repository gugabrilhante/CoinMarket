package br.brilhante.gustavo.coinmarcket.database.coin

import androidx.lifecycle.LiveData
import br.brilhante.gustavo.coinmarcket.database.AppDatabase
import br.brilhante.gustavo.coinmarcket.models.Coin

class CoinRepository {

    companion object {
        fun insertCoins(list: List<Coin>) {
            val coinDao = AppDatabase.INSTANCE?.CoinDao()
            coinDao?.let {
                coinDao.insertMovieList(list)
            }
        }

        fun getCoinLiveData(): LiveData<List<Coin>>? {
            val coinDao = AppDatabase.INSTANCE?.CoinDao()
            coinDao?.let {
                return it.getCoinList()
            }
            return null
        }

        fun getCoinLiveDataById(coinId: Int): LiveData<Coin>? {
            val coinDao = AppDatabase.INSTANCE?.CoinDao()
            coinDao?.let {
                return it.getCoinById(coinId)
            }
            return null
        }
    }

}