package br.com.luan2.nennospizza.view.activities.main

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.data.model.repositories.CartRepository
import br.com.luan2.nennospizza.data.model.repositories.PizzaRepository
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract
import br.com.squarebits.ninky.data.dao.LocalDbImplement

class MainActivityInteractor(val pizzaRepository: PizzaRepository, val cartRepository: CartRepository) : MainActivityContract.Interactor {

    lateinit var activity: Activity

    override fun getPizzaRequest(listener: MainActivityContract.Interactor.PizzaRequestInfo) {
        pizzaRepository.getPizzaListFromWeb(listener)
    }

    override fun getPizzaCache(listener: MainActivityContract.Interactor.PizzaCacheInfo) {
       pizzaRepository.getPizzaListFromCache(activity,listener)
    }

        override fun setItemToCart(pizza: Pizza, listener: CartActivityContract.Interactor.CartSaveItem) {
            val cart = LocalDbImplement<Cart>(activity).getDefault(Cart::class.java)
            cart?.let {  pizza.idCart = it.id }
            cartRepository.addItem(pizza,listener)
    }
}