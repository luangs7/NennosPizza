package br.com.luan2.nennospizza.data.model.repositories

import br.com.luan2.lgutilsk.utils.toJson
import br.com.luan2.nennospizza.data.dao.CartDao
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.Checkout
import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.retrofit.CallbackWrapper
import br.com.luan2.nennospizza.retrofit.ParseAPI
import br.com.luan2.nennospizza.rx.RxThread
import br.com.luan2.nennospizza.utils.Consts
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers


class CartRepository(val dao: CartDao, val api: ParseAPI, val rxThread: RxThread) {

    fun checkOut(cart: Cart, listener: CartActivityContract.Interactor.CartCheckout) {
        val subscription = CompositeDisposable()
        val checkout = Checkout()

        checkout.drinks = cart.drinks.map { it.id }
        checkout.pizzas = cart.pizzas

        subscription.add(
            api.checkout(Consts.CHECKOUT_ENDPOINT, checkout.toJson())
                .compose(rxThread.applyAsync())
                .subscribe({
                    nukeTables()
                    listener.onCheckoutSuccess()
                }, {
                    CallbackWrapper(it).onFailure()?.let { listener.onCheckoutFailure(it) }
                }
                )
        )
    }

    fun addItem(itemCart: Drinks, listener: CartActivityContract.Interactor.CartSaveItem) {
        Single.fromCallable { dao.insertDrink(itemCart) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                listener.onCartItemSave()
            }
            .doOnError { listener.onCartItemError(it.message!!) }
            .subscribe()
    }

    fun addItem(itemCart: Pizza, listener: CartActivityContract.Interactor.CartSaveItem) {
        Single.fromCallable { dao.insertPizza(itemCart) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                listener.onCartItemSave()
            }
            .doOnError { listener.onCartItemError(it.message!!) }
            .subscribe()
    }

    fun saveInitCart(listener: CartActivityContract.Interactor.CartInit) {
        val cart = Cart()
        Single.fromCallable {
            dao.insertAll(cart)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                cart.id = it.toInt()
                listener.onCartInitSave(cart)
            }
            .doOnError { listener.onCartInitError(it.message!!) }
            .subscribe()

    }

    fun getItensAll(cart: Cart, listener: CartActivityContract.Interactor.CarCacheInfo) {
        val combined = Single.zip(
            dao.getAll(),
            dao.getAllPizzas(cart.id),
            dao.getAllDrinks(cart.id),
            object : Function3<List<Cart>, List<Pizza>, List<Drinks>, Cart> {
                override fun apply(carts: List<Cart>, pizzas: List<Pizza>, drinks: List<Drinks>): Cart {
                    if(carts.isNotEmpty()) {
                        cart.pizzas = pizzas.toCollection(ArrayList())
                        cart.drinks = drinks.toCollection(ArrayList())
                        return cart
                    }else
                        return Cart()
                }
            })

        combined
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { listener.onCartSuccess(it) }
            .doOnError { listener.onCartError(it.message!!) }
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


    //deletes

    fun deletePizza(itemCart: Pizza, listener: CartActivityContract.Interactor.CartItemDelete) {
        Single.fromCallable { dao.delete(itemCart) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                listener.onCartItemDeleteSuccess()
            }
            .doOnError { listener.onCartItemDeleteError(it.message!!) }
            .subscribe()
    }

    fun deleteDrink(itemCart: Drinks, listener: CartActivityContract.Interactor.CartItemDelete) {
        Single.fromCallable { dao.delete(itemCart) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                listener.onCartItemDeleteSuccess()
            }
            .doOnError { listener.onCartItemDeleteError(it.message!!) }
            .subscribe()
    }

    fun nukeTables(){
        Single.fromCallable { dao.nukeTableDrinks() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        Single.fromCallable { dao.nukeTablePizzas() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}