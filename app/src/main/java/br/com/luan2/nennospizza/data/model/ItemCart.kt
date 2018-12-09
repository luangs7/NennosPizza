package br.com.luan2.nennospizza.data.model

import java.io.Serializable

interface ItemCart{
    var id: Int
    var idCart: Int
    var name: String
    var price: Double
    var type: CartType
}

enum class CartType : Serializable{
    DRINK,
    PIZZA
}