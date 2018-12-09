package br.com.luan2.nennospizza.retrofit.repositories

import br.com.luan2.nennospizza.data.dao.CartDao
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CartRepository(val dao: CartDao) {


    fun addItem(itemCart: Drinks, listener: CartActivityContract.Interactor.CartSaveItem) {
        dao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                val cart: Cart
                if (it.isNotEmpty()) {
                    cart = it.last().deserialize()
                } else {
                    cart = Cart()
                }
                cart.addDrink(itemCart)

                saveItemOrUpdate(cart, listener)
            }
            .doOnError { listener.onCartItemError(it.message!!) }
            .subscribe()
    }

    fun addItem(itemCart: Pizza, listener: CartActivityContract.Interactor.CartSaveItem) {
        dao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                val cart: Cart
                if (it.isNotEmpty()) {
                    cart = it.last().deserialize()
                } else {
                    cart = Cart()
                }
                cart.addPizza(itemCart)

                saveItemOrUpdate(cart, listener)
            }
            .doOnError { listener.onCartItemError(it.message!!) }
            .subscribe()
    }


    private fun saveItemOrUpdate(cart: Cart, listener: CartActivityContract.Interactor.CartSaveItem) {
        Completable.fromAction {
            dao.insertAll(cart)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                listener.onCartItemSave()
            }
            .doOnError { listener.onCartItemError(it.message!!) }
            .subscribe()

    }

    fun getCartList(listener: CartActivityContract.Interactor.CarCacheInfo) {
        dao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it.isNotEmpty()) {
                    listener.onCartSuccess(it.last())
                } else {
                    listener.onCartSuccess(null)
                }
            }
            .doOnError { listener.onCartError(it.message!!) }
            .subscribe()
    }

}