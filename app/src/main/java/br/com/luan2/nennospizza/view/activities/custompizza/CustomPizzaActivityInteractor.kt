package br.com.luan2.nennospizza.view.activities.custompizza

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.Ingredients
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.data.model.repositories.CartRepository
import br.com.luan2.nennospizza.data.model.repositories.IngredientsRepository
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract
import br.com.luan2.nennospizza.view.activities.pizzadetails.PizzaDetailsActivityContract
import br.com.squarebits.ninky.data.dao.LocalDbImplement

class CustomPizzaActivityInteractor(val ingredientsRepository: IngredientsRepository, val cartRepository: CartRepository) :
    CustomPizzaActivityContract.Interactor {

    lateinit var activity: Activity

    override fun getIngredientsRequest(listener: CustomPizzaActivityContract.Interactor.IngredientsRequestInfo) {
        ingredientsRepository.getIngredientsFromWeb(object : PizzaDetailsActivityContract.Interactor.IngredientsRequestInfo{
            override fun onRequestSuccess(ingredients: List<Ingredients>) {
                listener.onRequestSuccess(ingredients)
            }

            override fun onRequestError(error: String) {
                listener.onRequestError(error)
            }
        })
    }

    override fun getIngredientsCache(listener: CustomPizzaActivityContract.Interactor.IngredientsCacheInfo) {
        ingredientsRepository.getIngredientsFromCache(activity, object : PizzaDetailsActivityContract.Interactor.IngredientsCacheInfo{
            override fun onCachetSuccess(ingredients: List<Ingredients>) {
                listener.onCachetSuccess(ingredients)
            }

            override fun onCacheError(error: String) {
                listener.onCacheError(error)
            }
        })
    }


    override fun setItemToCart(pizza: Pizza, listener: CartActivityContract.Interactor.CartSaveItem) {
        val cart = LocalDbImplement<Cart>(activity).getDefault(Cart::class.java)
        cart?.let { pizza.idCart = it.id }
        cartRepository.addItem(pizza, listener)
    }
}