package br.com.luan2.nennospizza.data.model.repositories

import android.app.Activity
import br.com.luan2.lgutilsk.utils.debug
import br.com.luan2.nennospizza.data.model.Ingredients
import br.com.luan2.nennospizza.retrofit.CallbackWrapper
import br.com.luan2.nennospizza.retrofit.ParseAPI
import br.com.luan2.nennospizza.rx.RxThread
import br.com.luan2.nennospizza.view.activities.main.getListFromAssets
import br.com.luan2.nennospizza.view.activities.pizzadetails.PizzaDetailsActivityContract
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable

class IngredientsRepository(val api: ParseAPI, val rxThread: RxThread) {

    val subscription = CompositeDisposable()

    fun getIngredientsFromWeb(listener: PizzaDetailsActivityContract.Interactor.IngredientsRequestInfo) {
        subscription.add(
            api.getIngredients()
                .compose(rxThread.applyAsync())
                .subscribe({
                    listener.onRequestSuccess(it)
                }, {
                    CallbackWrapper(it).onFailure()?.let {
                        listener.onRequestError(it)
                    }
                })
        )
    }

    fun getIngredientsFromCache(activity: Activity, listener: PizzaDetailsActivityContract.Interactor.IngredientsCacheInfo) {
        try {
            val jsonIngradientsString = activity.getListFromAssets("responses/ingredients.json")
            val ingredientsResponse = Gson().fromJson(jsonIngradientsString, Array<Ingredients>::class.java)
            listener.onCachetSuccess(ingredientsResponse.toList())
        } catch (e: Exception) {
            listener.onCacheError(e.message!!)
            debug(e.message!!)
        }
    }




}