package br.com.luan2.nennospizza.view.activities.pizzadetails

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.luan2.lgutilsk.utils.SNACKBAR_DEFAULT_DURATION
import br.com.luan2.lgutilsk.utils.createSnackProgress
import br.com.luan2.lgutilsk.utils.dismissSnackProgress
import br.com.luan2.lgutilsk.utils.showSnackbar
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.Ingredients
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.view.activities.BaseActivity
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract
import com.bumptech.glide.Glide
import com.example.gitapi.adapter.IngredienteAdapter
import com.example.gitapi.adapter.OnIngredienteClick
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_pizza_details.*
import org.koin.android.ext.android.inject

class PizzaDetailsActivity : BaseActivity(), PizzaDetailsActivityContract.View, OnIngredienteClick, CartActivityContract.Interactor.CartSaveItem {

    val presenter: PizzaDetailsActivityPresenter by inject()

    lateinit var snackbar: Snackbar
    lateinit var adapter: IngredienteAdapter
    lateinit var pizza: Pizza


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_details)

        this.pizza = intent.getSerializableExtra("pizza") as Pizza

        presenter.provideView(this, this)

        presenter.providePizza(pizza)
    }

    override fun bindView() {
        toolbar.title = pizza.name
        setSupportActionBar(toolbar)

        listIngredientes.apply {
            layoutManager = LinearLayoutManager(this@PizzaDetailsActivity)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }

        pizza.imageUrl?.let { Glide.with(this).load(it).into(expandedImage) }

        addToCart.setOnClickListener {
            if(pizza.ingredients.count() > 0)
                presenter.putCart(pizza,this)
            else
                showSnackbar(rootView,"Selecione ao menos 1 sabor", SNACKBAR_DEFAULT_DURATION)
        }
        presenter.getIngredientes()
    }

    override fun onPizzaChanged(pizza: Pizza) {
        this.pizza = pizza
        onPizzaShow(adapter.items)
    }

    override fun onError(error: String) {
        showSnackbar(rootView,error, SNACKBAR_DEFAULT_DURATION)
    }


    override fun showSuccess(ingredients: List<Ingredients>) {
        onPizzaShow(ingredients)
    }

    override fun showProgress(message: String?) {
        snackbar = createSnackProgress(message!!)
        snackbar.show()
    }

    override fun hideProgress() = snackbar.dismissSnackProgress(this@PizzaDetailsActivity)


    override fun onIngrediente(ingredients: Ingredients) {
        presenter.manageIngredient(ingredients)
    }

    override fun onCartItemSave() {
        showSnackbar(rootView,"Added to cart", SNACKBAR_DEFAULT_DURATION)
    }

    override fun onCartItemError(error: String) {
        showSnackbar(rootView,error, SNACKBAR_DEFAULT_DURATION)
    }

    private fun onPizzaShow(ingredients: List<Ingredients>) {
        ingredients.forEach { it.isSelected = false }

        managePizzaChange(ingredients)


        adapter = IngredienteAdapter(ingredients, this, this).also {
            listIngredientes.adapter = it
        }
    }


    private fun managePizzaChange(ingredients: List<Ingredients>) {

        pizza.ingredients.forEach { i: Int ->
            ingredients.find { it.id == i }?.isSelected = true
        }

        pizza.putIngredientsObj(ingredients.filter { it.id in pizza.ingredients })

        addCartText.text = "Add to cart($${pizza.price})"
    }
}
