package br.com.luan2.nennospizza.data.model

data class Drinks(
   override var id: Int,
   override var name: String,
   override var price: Double,
   override var type: CartType
): ItemCart