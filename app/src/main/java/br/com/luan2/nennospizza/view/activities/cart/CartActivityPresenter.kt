package br.com.luan2.nennospizza.view.activities.cart

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.ItemCart

class CartActivityPresenter(val interactor: CartActivityInteractor) : CartActivityContract.Presenter {

    lateinit var view: CartActivityContract.View
    lateinit var activity: Activity


    override fun provideView(view: CartActivityContract.View, activity: Activity) {
        this.view = view
        this.activity = activity

        view.bindView()
    }

    override fun getCart() {
        view.showProgress()
        interactor.getCartCache(activity, object : CartActivityContract.Interactor.CarCacheInfo {
            override fun onCartSuccess(cart: Cart?) {
                cart?.let {
                    view.showSuccess(it)
                } ?: view.onError("Carrinho está vazio.")

                view.hideProgress()

            }

            override fun onCartError(error: String) {
                view.onError(error)
                view.hideProgress()
            }
        })
    }

    override fun doCheckout(cart: Cart, listener: CartActivityContract.Interactor.CartCheckout) {
        interactor.checkoutResponse(cart, listener)
    }

    override fun deleteItem(itemCart: ItemCart, listener: CartActivityContract.Interactor.CartItemDelete) {
        interactor.deleteItem(itemCart, listener)
    }
}