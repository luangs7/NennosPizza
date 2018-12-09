package br.com.luan2.nennospizza.view.activities.cart

import android.app.Activity
import br.com.luan2.nennospizza.data.model.*
import br.com.luan2.nennospizza.data.model.repositories.CartRepository
import br.com.squarebits.ninky.data.dao.LocalDbImplement

class CartActivityInteractor(val repository: CartRepository) : CartActivityContract.Interactor {


    override fun getCartCache(context :Activity,listener: CartActivityContract.Interactor.CarCacheInfo) {
        val cart = LocalDbImplement<Cart>(context).getDefault(Cart::class.java)
        cart?.let {
            repository.getItensAll(it,listener)
        }
    }


    override fun deleteItem(itemCart: ItemCart,listener:CartActivityContract.Interactor.CartItemDelete) {
        if (itemCart.type == CartType.PIZZA){
            repository.deletePizza(itemCart as Pizza, listener)
        }else{
            repository.deleteDrink(itemCart as Drinks,listener)
        }
    }

    override fun checkoutResponse(cart: Cart,listener: CartActivityContract.Interactor.CartCheckout) {
        repository.checkOut(cart,listener)
    }
}