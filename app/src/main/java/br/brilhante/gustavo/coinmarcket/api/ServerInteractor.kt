package br.brilhante.gustavo.coinmarcket.api

import br.brilhante.gustavo.coinmarcket.models.CoinResponse
import com.google.gson.Gson
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServerInteractor {

    private val serverCalls: ServerCalls

    companion object {
        const val BASE_URL = "https://api.coinmarketcap.com/v2/"
        const val STRUCTURE = "array"
        const val SORT = "rank"
    }

    init {
        val clientBuilder = OkHttpClient.Builder()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        serverCalls = retrofit
            .create(ServerCalls::class.java)
    }

    fun donwloadCoins(limit: Int): Single<CoinResponse> {
        return serverCalls.coinsList(limit, SORT, STRUCTURE)
    }
}