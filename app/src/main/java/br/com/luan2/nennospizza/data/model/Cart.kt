package br.com.luan2.nennospizza.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "CartTable")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @Ignore
    var drinks: ArrayList<Drinks> = ArrayList(),
    @Ignore
    var pizzas: ArrayList<Pizza> = ArrayList(),
    var cartJson:String = "",
    var priceTotal:Double = 0.0
) {
    fun addPizza(itemCart: Pizza) {
       this.pizzas.add(itemCart)
        this.cartJson = Gson().toJson(this)
    }

    fun addDrink(itemCart: Drinks) {
        this.drinks.add(itemCart)
        this.cartJson = Gson().toJson(this)
    }
    fun deserialize():Cart{
        return Gson().fromJson(cartJson,Cart::class.java)
    }

    val items: List<ItemCart>
       get() = pizzas + drinks

    var totalPrice : Double = 0.0
        get() = if(items.isEmpty()) 0.0 else this.items.map(ItemCart::price).reduce{acc: Double, d: Double -> acc + d }
}
