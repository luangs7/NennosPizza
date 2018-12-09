package br.com.luan2.nennospizza.data.model

import com.google.gson.annotations.SerializedName

open class Pizza(
    @SerializedName("name")
    override var name: String,
    override var id: Int,
    override var price: Double,

    @SerializedName("imageUrl")
    var imageUrl: String?,
    @SerializedName("ingredients")
    var ingredients: List<Int>,

    var basePrice: Double = 0.0,
    var totalPrice: Double = 0.0,
    var ingredientNames: String,
    var ingredientsObj: List<Ingredients>

) : ItemCart {
    override var type: CartType = CartType.PIZZA

    fun putIngredientsObj(list: List<Ingredients>) {
        this.ingredientsObj = list
        ingredientNames = list.map(Ingredients::name).joinToString()

        if (ingredientsObj.isEmpty()) {
            totalPrice = 0.0
        } else {
            totalPrice = ingredientsObj.map(Ingredients::price).reduce { left, right -> left + right }
        }
        totalPrice += basePrice
    }
}