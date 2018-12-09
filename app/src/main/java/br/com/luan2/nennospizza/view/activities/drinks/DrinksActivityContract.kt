package br.com.luan2.nennospizza.view.activities.drinks

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract

interface DrinksActivityContract {

    interface Presenter{
        fun getDrinks()
        fun putCart(itemCart: Drinks, listener: CartActivityContract.Interactor.CartSaveItem)
        fun provideView(view: DrinksActivityContract.View, activity: Activity)
    }


    interface View {
        fun bindView()
        fun onError(error: String)
        fun showSuccess(drinks: List<Drinks>)
        fun showProgress(message:String? = "Buscando dados")
        fun hideProgress()
    }

    interface Interactor {

        interface DrinkRequestInfo{
            fun onRequestSuccess(drinks: List<Drinks>)
            fun onRequestError(error: String)
        }

        interface DrinkCacheInfo{
            fun onCachetSuccess(drinks: List<Drinks>)
            fun onCacheError(error: String)
        }

        interface CartItemAdd{
            fun onCartSuccess(drink: Drinks)
            fun onCartError(error: String)
        }

        fun getDrinkRequest(listener:DrinkRequestInfo)
        fun getDrinkCache(listener:DrinkCacheInfo)
        fun setItemToCart(itemCart: Drinks, listener: CartActivityContract.Interactor.CartSaveItem)

    }
}