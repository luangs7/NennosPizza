package br.com.luan2.nennospizza.view.activities.cart

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.ItemCart

interface CartActivityContract {

    interface Presenter{
        fun getCart()
        fun provideView(view: View, activity: Activity)
        fun doCheckout(cart: Cart,listener: CartActivityContract.Interactor.CartCheckout)
        fun deleteItem(itemCart: ItemCart, listener:CartActivityContract.Interactor.CartItemDelete)
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

        interface CartInit{
            fun onCartInitSave(cart: Cart)
            fun onCartInitError(error: String)
        }

        interface CartItemDelete{
            fun onCartItemDeleteSuccess()
            fun onCartItemDeleteError(error: String)
        }

        interface CartCheckout{
            fun onCheckoutSuccess()
            fun onCheckoutFailure(error: String)
        }

        fun checkoutResponse(cart: Cart,listener: CartCheckout)
        fun getCartCache(context :Activity,listener: CarCacheInfo)
        fun deleteItem(itemCart: ItemCart, listener:CartActivityContract.Interactor.CartItemDelete)
    }
}