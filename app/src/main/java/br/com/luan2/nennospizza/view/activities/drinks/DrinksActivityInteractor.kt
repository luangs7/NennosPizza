package br.com.luan2.nennospizza.view.activities.drinks

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.data.model.ItemCart
import br.com.luan2.nennospizza.retrofit.repositories.CartRepository
import br.com.luan2.nennospizza.retrofit.repositories.DrinkRepository
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract

class DrinksActivityInteractor(val repository: DrinkRepository, val cartRepository: CartRepository) : DrinksActivityContract.Interactor {

    lateinit var activity: Activity

    override fun getDrinkRequest(listener: DrinksActivityContract.Interactor.DrinkRequestInfo) {
        repository.getDrinksFromWeb(listener)
    }

    override fun getDrinkCache(listener: DrinksActivityContract.Interactor.DrinkCacheInfo) {
        repository.getDrinksFromCache(activity,listener)
    }

    override fun setItemToCart(itemCart: Drinks, listener: CartActivityContract.Interactor.CartSaveItem) {
        cartRepository.addItem(itemCart,listener)
    }
}