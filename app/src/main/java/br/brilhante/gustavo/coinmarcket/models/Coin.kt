package br.brilhante.gustavo.coinmarcket.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coin")
data class Coin(

    @field:SerializedName("symbol")
    var symbol: String? = null,

    @field:SerializedName("circulating_supply")
    var circulatingSupply: Double? = null,

    @field:SerializedName("last_updated")
    var lastUpdated: Int? = null,

    @field:SerializedName("total_supply")
    var totalSupply: Double? = null,

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("max_supply")
    var maxSupply: Double? = null,

    @field:SerializedName("rank")
    var rank: Int? = null,

    @PrimaryKey
    @field:SerializedName("id")
    var id: Int? = null,

    @field:SerializedName("website_slug")
    var websiteSlug: String? = null,

    @Embedded
    @field:SerializedName("quotes")
    var quotes: Quotes? = null
)