package com.example.gitapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.ItemCart
import br.com.luan2.nennospizza.view.adapters.cart.OnCartItem

class CartAdapter constructor(private val items: List<ItemCart>,
                              private val onItemClicked: OnCartItem,
                              private val context:Context)
    : RecyclerView.Adapter<CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cart, parent, false)
        return CartViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val repository = items[position]
        holder.bind(repository, onItemClicked, position)
    }

    override fun getItemCount(): Int = items.size

}
