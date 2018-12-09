package br.com.luan2.nennospizza.view.activities.pizzadetails

import android.app.Activity
import br.com.luan2.nennospizza.data.model.Ingredients
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract

class PizzaDetailsActivityPresenter(val interactor: PizzaDetailsActivityInteractor) :
    PizzaDetailsActivityContract.Presenter {

    lateinit var view: PizzaDetailsActivityContract.View
    lateinit var activity: Activity
    lateinit var pizza: Pizza

    override fun provideView(view: PizzaDetailsActivityContract.View, activity: Activity) {
        this.view = view
        this.activity = activity
        interactor.activity = activity
        view.bindView()
    }

    override fun providePizza(pizza: Pizza) {
        this.pizza = pizza
    }

    override fun onPizzaChanged(pizza: Pizza) {
        view.onPizzaChanged(pizza)
    }

    override fun getIngredientes() {
        view.showProgress()
        interactor.getIngredientsRequest(object : PizzaDetailsActivityContract.Interactor.IngredientsRequestInfo {
            override fun onRequestSuccess(ingredients: List<Ingredients>) {
                view.showSuccess(ingredients)
                view.hideProgress()
            }

            override fun onRequestError(error: String) {
                interactor.getIngredientsCache(object : PizzaDetailsActivityContract.Interactor.IngredientsCacheInfo {
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