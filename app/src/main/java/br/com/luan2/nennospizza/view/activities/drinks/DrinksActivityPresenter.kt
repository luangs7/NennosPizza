package br.com.luan2.nennospizza.view.activities.drinks

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract
import br.com.squarebits.ninky.data.dao.LocalDbImplement

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
        interactor.getDrinkRequest(object : DrinksActivityContract.Interactor.DrinkRequestInfo{
            override fun onRequestSuccess(drinks: List<Drinks>) {
                view.showSuccess(drinks)
                view.hideProgress()
            }

            override fun onRequestError(error: String) {
                interactor.getDrinkCache(object : DrinksActivityContract.Interactor.DrinkCacheInfo{
                    override fun onCachetSuccess(drinks: List<Drinks>) {
                        view.showSuccess(drinks)
                        view.hideProgress()
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
        val cart = LocalDbImplement<Cart>(activity).getDefault(Cart::class.java)
        cart?.let {  itemCart.idCart = it.id }
        interactor.setItemToCart(itemCart,listener)
    }

}