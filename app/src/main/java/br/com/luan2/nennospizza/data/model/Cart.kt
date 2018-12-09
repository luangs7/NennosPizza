package br.com.luan2.nennospizza.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "CartTable")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @Ignore
    var drinks: ArrayList<Drinks> = ArrayList(),
    @Ignore
    var pizzas: ArrayList<Pizza> = ArrayList()
) {

    val items: List<ItemCart>
       get() = pizzas + drinks

    var totalPrice : Double = 0.0
        get() = if(items.isEmpty()) 0.0 else this.items.map(ItemCart::price).reduce{acc: Double, d: Double -> acc + d }
}
