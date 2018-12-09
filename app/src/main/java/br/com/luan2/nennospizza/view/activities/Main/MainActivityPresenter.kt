package br.com.luan2.nennospizza.view.activities.main

import android.app.Activity
import br.com.luan2.lgutilsk.utils.startActivity
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.data.model.PizzaResponse
import br.com.luan2.nennospizza.view.activities.IngredientsActivity
import br.com.luan2.nennospizza.view.activities.cart.CartActivity
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract

class MainActivityPresenter(val interactor:MainActivityInteractor): MainActivityContract.Presenter {

    lateinit var view: MainActivityContract.View
    lateinit var activity: Activity

    override fun provideView(view: MainActivityContract.View, activity: Activity) {
        this.view = view
        this.activity = activity
        interactor.activity = activity
        view.bindView()
    }

    override fun getPizzas() {
        view.showProgress()
        interactor.getPizzaRequest(object : MainActivityContract.Interactor.PizzaRequestInfo{
            override fun onRequestSuccess(pizzaResponse: PizzaResponse) {
                view.showSuccess(pizzaResponse.pizzas)
                view.hideProgress()
            }

            override fun onRequestError(error: String) {
                interactor.getPizzaCache(object : MainActivityContract.Interactor.PizzaCacheInfo{
                    override fun onCachetSuccess(pizzaResponse: PizzaResponse) {
                        view.showSuccess(pizzaResponse.pizzas)
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

    override fun openDetails(pizza: Pizza) {
        activity.startActivity(IngredientsActivity())
    }

    override fun putCart(pizza: Pizza,listener: CartActivityContract.Interactor.CartSaveItem) {
        interactor.setItemToCart(pizza,listener)
    }

    override fun goToCart() {
        activity.startActivity(CartActivity())
    }

    override fun gotToNewPizza() {
        activity.startActivity(IngredientsActivity())
    }
}