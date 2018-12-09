package br.com.luan2.nennospizza.view.activities.Main

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.data.model.PizzaResponse
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract

interface MainActivityContract {

    interface Presenter {
        fun getPizzas()
        fun openDetails(pizza: Pizza)
        fun putCart(pizza: Pizza, listener: CartActivityContract.Interactor.CartSaveItem)
        fun goToCart()
        fun gotToNewPizza()
        fun provideView(view: MainActivityContract.View, activity: Activity)
    }


    interface View {
        fun bindView()
        fun onError(error: String)
        fun showSuccess(pizzas: List<Pizza>)
        fun hideKeyboard()
        fun showProgress(message: String? = "Buscando dados")
        fun hideProgress()
    }

    interface Interactor {

        interface PizzaRequestInfo {
            fun onRequestSuccess(pizzaResponse: PizzaResponse)
            fun onRequestError(error: String)
        }

        interface PizzaCacheInfo {
            fun onCachetSuccess(pizzaResponse: PizzaResponse)
            fun onCacheError(error: String)
        }

        interface CartItemAdd {
            fun onCartSuccess(pizza: Pizza)
            fun onCartError(error: String)
        }

        fun getPizzaRequest(listener: PizzaRequestInfo)
        fun getPizzaCache(listener: PizzaCacheInfo)
        fun setItemToCart(pizza: Pizza, listener: CartActivityContract.Interactor.CartSaveItem)

    }
}