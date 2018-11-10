package br.brilhante.gustavo.coinmarcket.api

import br.brilhante.gustavo.coinmarcket.models.CoinResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerCalls {
    @GET("ticker")
    fun coinsList(
        @Query("limit") limit: Int,
        @Query("sort") sort: String,
        @Query("structure") structure: String
    ): Single<CoinResponse>
}