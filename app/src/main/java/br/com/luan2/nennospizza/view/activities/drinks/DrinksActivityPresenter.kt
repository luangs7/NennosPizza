package br.com.luan2.nennospizza.view.activities.drinks

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.data.model.ItemCart
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract

class DrinksActivityPresenter(val interactor: DrinksActivityInteractor) : DrinksActivityContract.Presenter {

    lateinit var view: DrinksActivityContract.View
    lateinit var activity: Activity


    override fun provideView(view: DrinksActivityContract.View, activity: Activity) {
        this.view = view
        this.activity = activity
        interactor.activity = activity

        view.bindView()
    }

    override fun getDrinks() {
        view.showProgress()
        interactor.getDrinkCache(object : DrinksActivityContract.Interactor.DrinkCacheInfo{
            override fun onCachetSuccess(drinks: List<Drinks>) {
                view.showSuccess(drinks)
                view.hideProgress()
            }

            override fun onCacheError(error: String) {
                interactor.getDrinkCache(object : DrinksActivityContract.Interactor.DrinkCacheInfo{
                    override fun onCachetSuccess(drinks: List<Drinks>) {
                        view.showSuccess(drinks)
                    }

                    override fun onCacheError(error: String) {
                        view.onError(error)
                        view.hideProgress()
                    }
                })
            }
        })
    }

    override fun putCart( itemCart: Drinks, listener: CartActivityContract.Interactor.CartSaveItem) {
        interactor.setItemToCart(itemCart,listener)
    }

}