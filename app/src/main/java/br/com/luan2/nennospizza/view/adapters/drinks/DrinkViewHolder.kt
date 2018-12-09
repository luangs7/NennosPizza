package com.example.gitapi.adapter


import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.Drinks

class DrinkViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {

    val nameDrink by lazy { view.findViewById(R.id.nameDrink) as TextView }
    val add by lazy { view.findViewById(R.id.add) as ImageView }
    val price by lazy { view.findViewById(R.id.price) as TextView }

    fun bind(item: Drinks, onItemClicked: OnClickDrink) {

        nameDrink.text = item.name
        price.text = "$ ${item.price}"

        add.setOnClickListener { onItemClicked.onDrinkToCart(item) }

    }
}
