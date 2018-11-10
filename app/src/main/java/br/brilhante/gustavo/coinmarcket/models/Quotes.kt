package br.brilhante.gustavo.coinmarcket.models

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Quotes(

    @Embedded
	@field:SerializedName("USD")
	var uSD: USD? = null
)