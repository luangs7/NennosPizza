package br.com.luan2.nennospizza.data.model

import com.google.gson.annotations.SerializedName

open class Checkout{
    @SerializedName("pizzas")
    var pizzas: List<Pizza> = emptyList()
    @SerializedName("drinks")
    var drinks: List<Int> = emptyList()

}