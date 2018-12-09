package br.com.luan2.nennospizza.view.activities.cart

import br.com.luan2.nennospizza.data.model.ItemCart
import br.com.luan2.nennospizza.retrofit.repositories.CartRepository

class CartActivityInteractor(val repository: CartRepository) : CartActivityContract.Interactor {


    override fun getCartCache(listener: CartActivityContract.Interactor.CarCacheInfo) {
        repository.getCartList(listener)
    }


}