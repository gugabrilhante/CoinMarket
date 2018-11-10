package br.brilhante.gustavo.coinmarcket.models

import com.google.gson.annotations.SerializedName

data class CoinResponse(

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("data")
	val data: List<Coin?>? = null
)