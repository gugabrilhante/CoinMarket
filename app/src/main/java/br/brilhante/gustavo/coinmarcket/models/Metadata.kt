package br.brilhante.gustavo.coinmarcket.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Metadata(

    @field:SerializedName("num_cryptocurrencies")
    var numCryptocurrencies: Int? = null,

    @Ignore
    @field:SerializedName("error")
    var error: Any? = null,

    @field:SerializedName("timestamp")
    var timestamp: Int? = null
)