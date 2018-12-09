package com.example.gitapi.adapter


import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.luan2.lgutilsk.utils.loadPlaceholder
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.Pizza
import com.bumptech.glide.Glide
import kotlin.math.roundToInt

class PizzaViewHolder(view: View,val context: Context) : RecyclerView.ViewHolder(view) {

    val pizzaName by lazy { view.findViewById(R.id.pizzaName) as TextView }
    val ingredients by lazy { view.findViewById(R.id.ingredients) as TextView }
    val image by lazy { view.findViewById(R.id.image) as ImageView }
    val price by lazy { view.findViewById(R.id.price) as TextView }
    val priceTag by lazy { view.findViewById(R.id.pricetag) as RelativeLayout }
    val itemLayout by lazy { view.findViewById(R.id.item) as LinearLayout }

    fun bind(item: Pizza, clickPizza: OnClickPizza) {
        item.imageUrl?.let {
            Glide.with(context)
                .load(it)
                .into(image)
        } ?: image.loadPlaceholder()

        pizzaName.text = item.name
        ingredients.text = item.ingredientNames
        price.text = "$${item.price.roundToInt()}"

        priceTag.setOnClickListener { clickPizza.onItemToCart(item) }

        itemLayout.setOnClickListener { clickPizza.onItemDetails(item) }

    }
}
