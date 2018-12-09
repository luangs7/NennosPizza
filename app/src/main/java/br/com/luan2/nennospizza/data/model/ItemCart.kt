package br.com.luan2.nennospizza.data.model

interface ItemCart{
    var id: Int
    var idCart: Int
    var name: String
    var price: Double
    var type: CartType
}

enum class CartType{
    DRINK,
    PIZZA
}