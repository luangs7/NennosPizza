package br.com.luan2.nennospizza.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Pizza")
open class Pizza : ItemCart, Serializable {
    @SerializedName("name")
    override var name: String = ""

    @PrimaryKey(autoGenerate = true)
    override var id: Int = 0

    override var idCart: Int = 0

    override var price: Double = 0.0

    @SerializedName("imageUrl")
    var imageUrl: String? = ""

    @Ignore
    @SerializedName("ingredients")
    var ingredients: List<Int> = emptyList()

    var basePrice: Double = 0.0

    @Ignore
    var ingredientNames: String = ""

    @Ignore
    var ingredientsObj: List<Ingredients> = emptyList()

    @Ignore
    override var type: CartType = CartType.PIZZA

    var ingredientsJson : String = ""

    fun putIngredientsObj(list: List<Ingredients>) {
        this.ingredientsObj = list
        ingredientNames = list.map(Ingredients::name).joinToString()

        if (ingredientsObj.isEmpty()) {
            price = 0.0
        } else {
            price = ingredientsObj.map(Ingredients::price).reduce { left, right -> left + right }
        }
        price += basePrice
    }
}