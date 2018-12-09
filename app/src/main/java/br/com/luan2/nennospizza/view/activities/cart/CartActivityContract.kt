package br.com.luan2.nennospizza.view.activities.cart

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Cart

interface CartActivityContract {

    interface Presenter{
        fun getCart()
        fun provideView(view: View, activity: Activity)
        fun doCheckout()
    }


    interface View {
        fun bindView()
        fun onError(error: String)
        fun showSuccess(cart: Cart)
        fun showProgress(message:String? = "Buscando dados")
        fun hideProgress()
    }

    interface Interactor {

        interface CarCacheInfo{
            fun onCartSuccess(cart: Cart?)
            fun onCartError(error: String)
        }

        interface CartSaveItem{
            fun onCartItemSave()
            fun onCartItemError(error: String)
        }

        fun getCartCache(listener: CarCacheInfo)
    }
}