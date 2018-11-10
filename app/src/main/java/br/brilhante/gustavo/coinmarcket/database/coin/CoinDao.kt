package br.brilhante.gustavo.coinmarcket.database.coin

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.brilhante.gustavo.coinmarcket.models.Coin

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(coinList: List<Coin>)

    @Query("SELECT * FROM Coin")
    fun getCoinList(): LiveData<List<Coin>>

    @Query("SELECT * FROM Coin WHERE id =:coinId LIMIT 1")
    fun getCoinById(coinId: Int): LiveData<Coin>
}