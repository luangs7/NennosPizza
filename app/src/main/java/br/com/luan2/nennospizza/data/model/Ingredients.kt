package br.com.luan2.nennospizza.data.model

import java.io.Serializable

data class Ingredients(
    var id: Int,
    var name: String,
    var price: Double,
    var isSelected:Boolean = false

): Serializable