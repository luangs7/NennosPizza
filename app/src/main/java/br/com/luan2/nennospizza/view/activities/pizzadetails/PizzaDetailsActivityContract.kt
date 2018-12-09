package br.com.luan2.nennospizza.view.activities.pizzadetails

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Ingredients
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract

interface PizzaDetailsActivityContract {

    interface Presenter {
        fun getIngredientes()
        fun putCart(pizza: Pizza, listener: CartActivityContract.Interactor.CartSaveItem)
        fun manageIngredient(ingredients: Ingredients)
        fun provideView(view: PizzaDetailsActivityContract.View, activity: Activity)
        fun providePizza(pizza: Pizza)
        fun onPizzaChanged(pizza: Pizza)
    }


    interface View {
        fun bindView()
        fun onError(error: String)
        fun showSuccess(ingredients: List<Ingredients>)
        fun showProgress(message: String? = "Buscando dados")
        fun hideProgress()
        fun onPizzaChanged(pizza: Pizza)
    }

    interface Interactor {

        interface IngredientsRequestInfo {
            fun onRequestSuccess(ingredients: List<Ingredients>)
            fun onRequestError(error: String)
        }

        interface IngredientsCacheInfo {
            fun onCachetSuccess(ingredients: List<Ingredients>)
            fun onCacheError(error: String)
        }

        interface CartItemAdd {
            fun onCartSuccess(pizza: Pizza)
            fun onCartError(error: String)
        }

        fun getIngredientsRequest(listener: IngredientsRequestInfo)
        fun getIngredientsCache(listener: IngredientsCacheInfo)
        fun setItemToCart(pizza: Pizza, listener: CartActivityContract.Interactor.CartSaveItem)

    }
}