package br.com.luan2.nennospizza.data.model.repositories

import android.app.Activity
import br.com.luan2.lgutilsk.utils.debug
import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.retrofit.CallbackWrapper
import br.com.luan2.nennospizza.retrofit.ParseAPI
import br.com.luan2.nennospizza.rx.RxThread
import br.com.luan2.nennospizza.view.activities.drinks.DrinksActivityContract
import br.com.luan2.nennospizza.view.activities.getListFromAssets
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable

class DrinkRepository(val rxThread: RxThread, val api: ParseAPI) {

    var subscription = CompositeDisposable()

    fun getDrinksFromWeb(listener: DrinksActivityContract.Interactor.DrinkRequestInfo) {

        subscription.add(
            api.getDrinks()
                .compose(rxThread.applyAsync())
                .subscribe({
                    listener.onRequestSuccess(it)
                }, {
                    CallbackWrapper(it).onFailure()?.let { listener.onRequestError(it) }
                })
        )
    }

    fun getDrinksFromCache(activity: Activity, listener: DrinksActivityContract.Interactor.DrinkCacheInfo) {
        try {
            val jsonString = activity.getListFromAssets("responses/drinks.json")
            val drinkResponse = Gson().fromJson(jsonString, Array<Drinks>::class.java)

            listener.onCachetSuccess(drinkResponse.toList())
        } catch (e: Exception) {
            listener.onCacheError(e.message!!)
            debug(e.message!!)
        }
    }

}