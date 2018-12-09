package br.com.luan2.nennospizza.view.adapters.cart

import br.com.luan2.nennospizza.data.model.ItemCart

interface OnCartItem {
    fun onItemDelete(itemCart: ItemCart)
}