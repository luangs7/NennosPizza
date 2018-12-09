package com.example.gitapi.adapter

import br.com.luan2.nennospizza.data.model.Pizza

interface OnClickPizza {
    fun onItemToCart(pizza: Pizza)
    fun onItemDetails(pizza: Pizza)
}