package com.example.gitapi.adapter


import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.ItemCart
import br.com.luan2.nennospizza.utils.Consts
import br.com.luan2.nennospizza.view.adapters.cart.OnCartItem

class CartViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {

    val name by lazy { view.findViewById(R.id.nameIngredient) as TextView }
    val delete by lazy { view.findViewById(R.id.delete) as ImageView }
    val price by lazy { view.findViewById(R.id.price) as TextView }
    val item by lazy { view.findViewById(R.id.item) as RelativeLayout }

    fun bind(item: ItemCart, onItemClicked: OnCartItem, position:Int) {

        name.text = item.name
        price.text = "$ ${String.format(Consts.numberFormat,item.price)}"

        delete.setOnClickListener { onItemClicked.onItemDelete(item,position) }

    }
}
