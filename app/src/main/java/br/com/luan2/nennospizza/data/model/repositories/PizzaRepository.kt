package br.com.luan2.nennospizza.data.model.repositories

import android.app.Activity
import br.com.luan2.lgutilsk.utils.debug
import br.com.luan2.nennospizza.data.model.Ingredients
import br.com.luan2.nennospizza.data.model.PizzaResponse
import br.com.luan2.nennospizza.retrofit.CallbackWrapper
import br.com.luan2.nennospizza.retrofit.ParseAPI
import br.com.luan2.nennospizza.rx.RxThread
import br.com.luan2.nennospizza.view.activities.main.MainActivityContract
import br.com.luan2.nennospizza.view.activities.main.getListFromAssets
import com.google.gson.Gson
import io.reactivex.rxkotlin.Observables

class PizzaRepository(val rxThread: RxThread, val api: ParseAPI) {

    fun getPizzaListFromWeb(listener: MainActivityContract.Interactor.PizzaRequestInfo) {
        val combined = Observables.zip(
            api.getPizzaMain().compose(rxThread.applyAsync()),
            api.getIngredients().compose(rxThread.applyAsync())
        ) { pizzaResponse, ingredients ->
            createPizza(pizzaResponse, ingredients)
        }

        combined.subscribe({
            listener.onRequestSuccess(it)
        }, {
            CallbackWrapper(it).onFailure()?.let { listener.onRequestError(it) }
        })


    }

    fun getPizzaListFromCache(activity: Activity, listener: MainActivityContract.Interactor.PizzaCacheInfo) {
        try {
            val jsonPizzaString = activity.getListFromAssets("responses/pizzas.json")
            val jsonIngradientsString = activity.getListFromAssets("responses/ingredients.json")
            val pizzaResponse = Gson().fromJson(jsonPizzaString, PizzaResponse::class.java)

            val ingredientsResponse = Gson().fromJson(jsonIngradientsString, Array<Ingredients>::class.java)
            listener.onCachetSuccess(createPizza(pizzaResponse, ingredientsResponse.toList()))
        } catch (e: Exception) {
            listener.onCacheError(e.message!!)
            debug(e.message!!)
        }
    }


    private fun createPizza(pizzaResponse: PizzaResponse, ingredients: List<Ingredients>): PizzaResponse {
        pizzaResponse.pizzas.map {
            val pizza = it
            pizza.putIngredientsObj(ingredients.filter { it.id in pizza.ingredients })
        }
        return pizzaResponse
    }
}