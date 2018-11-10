package br.brilhante.gustavo.coinmarcket.dataproviders

import br.brilhante.gustavo.coinmarcket.api.ServerInteractor
import br.brilhante.gustavo.coinmarcket.models.Coin
import br.brilhante.gustavo.coinmarcket.models.CoinResponse
import io.reactivex.Single

object CoinRequestManager {

    fun getCoinList(): Single<List<Coin>> {
        return ServerInteractor()
            .donwloadCoins(10)
            .map { response: CoinResponse -> response.data?.filterNotNull() ?: kotlin.run { emptyList<Coin>() } }
    }

}