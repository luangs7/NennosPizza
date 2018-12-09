package br.com.luan2.nennospizza.view.activities.pizzadetails

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.data.model.repositories.CartRepository
import br.com.luan2.nennospizza.data.model.repositories.IngredientsRepository
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract
import br.com.squarebits.ninky.data.dao.LocalDbImplement

class PizzaDetailsActivityInteractor(val ingredientsRepository: IngredientsRepository, val cartRepository: CartRepository) :
    PizzaDetailsActivityContract.Interactor {

    lateinit var activity: Activity

    override fun getIngredientsRequest(listener: PizzaDetailsActivityContract.Interactor.IngredientsRequestInfo) {
        ingredientsRepository.getIngredientsFromWeb(listener)
    }

    override fun getIngredientsCache(listener: PizzaDetailsActivityContract.Interactor.IngredientsCacheInfo) {
        ingredientsRepository.getIngredientsFromCache(activity,listener)
    }


    override fun setItemToCart(pizza: Pizza, listener: CartActivityContract.Interactor.CartSaveItem) {
        val cart = LocalDbImplement<Cart>(activity).getDefault(Cart::class.java)
        cart?.let { pizza.idCart = it.id }
        cartRepository.addItem(pizza, listener)
    }
}