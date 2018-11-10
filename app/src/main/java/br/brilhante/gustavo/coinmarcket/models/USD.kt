package br.brilhante.gustavo.coinmarcket.models

import com.google.gson.annotations.SerializedName

data class USD(

	@field:SerializedName("percent_change_1h")
	var percentChange1h: Double? = null,

	@field:SerializedName("market_cap")
	var marketCap: Double? = null,

	@field:SerializedName("percent_change_24h")
	var percentChange24h: Double? = null,

	@field:SerializedName("price")
	var price: Double? = null,

	@field:SerializedName("volume_24h")
	var volume24h: Double? = null,

	@field:SerializedName("percent_change_7d")
	var percentChange7d: Double? = null
)