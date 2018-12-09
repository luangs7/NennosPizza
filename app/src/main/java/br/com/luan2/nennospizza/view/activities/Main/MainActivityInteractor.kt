package br.com.luan2.nennospizza.view.activities.Main

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.retrofit.repositories.CartRepository
import br.com.luan2.nennospizza.retrofit.repositories.PizzaRepository
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract

class MainActivityInteractor(val pizzaRepository: PizzaRepository, val cartRepository: CartRepository) : MainActivityContract.Interactor {

    lateinit var activity: Activity

    override fun getPizzaRequest(listener: MainActivityContract.Interactor.PizzaRequestInfo) {
        pizzaRepository.getPizzaListFromWeb(listener)
    }

    override fun getPizzaCache(listener: MainActivityContract.Interactor.PizzaCacheInfo) {
       pizzaRepository.getPizzaListFromCache(activity,listener)
    }

        override fun setItemToCart(pizza: Pizza, listener: CartActivityContract.Interactor.CartSaveItem) {
        cartRepository.addItem(pizza,listener)
    }
}