package br.com.luan2.nennospizza.view.activities.custompizza

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Ingredients
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract
import com.google.gson.Gson

class CustomPizzaActivityPresenter(val interactor: CustomPizzaActivityInteractor) :
    CustomPizzaActivityContract.Presenter {

    lateinit var view: CustomPizzaActivityContract.View
    lateinit var activity: Activity
    lateinit var pizza: Pizza

    override fun provideView(pizza: Pizza,view: CustomPizzaActivityContract.View, activity: Activity) {
        this.view = view
        this.activity = activity
        interactor.activity = activity
        this.pizza = pizza

        view.bindView()
    }

    override fun createPizza(pizza: Pizza,ingredients: List<Ingredients>) {
        pizza.name = "Custom Pizza"
        pizza.putIngredientsObj(ingredients.filter { it.id in pizza.ingredients })
        pizza.ingredientsJson = Gson().toJson(pizza.ingredients)

        view.onPizzaCreated(pizza)
    }

    override fun onPizzaChanged(pizza: Pizza) {
        view.onPizzaChanged(pizza)
    }

    override fun getIngredientes() {
        view.showProgress()
        interactor.getIngredientsRequest(object : CustomPizzaActivityContract.Interactor.IngredientsRequestInfo {
            override fun onRequestSuccess(ingredients: List<Ingredients>) {
                view.showSuccess(ingredients)
                view.hideProgress()
            }

            override fun onRequestError(error: String) {
                interactor.getIngredientsCache(object : CustomPizzaActivityContract.Interactor.IngredientsCacheInfo {
                    override fun onCachetSuccess(ingredients: List<Ingredients>) {
                        view.showSuccess(ingredients)
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


    override fun putCart(pizza: Pizza, listener: CartActivityContract.Interactor.CartSaveItem) {
        interactor.setItemToCart(pizza, listener)
    }

    override fun manageIngredient(ingredients: Ingredients) {
        val lista = pizza.ingredients.toCollection(ArrayList())

        if (ingredients.isSelected) {
            lista.remove(ingredients.id)
        } else {
            lista.add(ingredients.id)
        }

        pizza.ingredients = lista

        onPizzaChanged(pizza)
    }


}