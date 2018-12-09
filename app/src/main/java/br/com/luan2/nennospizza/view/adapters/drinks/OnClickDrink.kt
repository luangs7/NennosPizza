package com.example.gitapi.adapter

import br.com.luan2.nennospizza.data.model.Drinks

interface OnClickDrink {
    fun onDrinkToCart(drinks: Drinks)
}