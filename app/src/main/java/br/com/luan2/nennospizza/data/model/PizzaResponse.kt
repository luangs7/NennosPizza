package br.com.luan2.nennospizza.data.model

data class PizzaResponse(
    var basePrice: Int,
    var pizzas: List<Pizza>
)