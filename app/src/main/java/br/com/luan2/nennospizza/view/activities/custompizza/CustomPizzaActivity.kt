package br.com.luan2.nennospizza.view.activities.custompizza

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.luan2.lgutilsk.utils.createSnackProgress
import br.com.luan2.lgutilsk.utils.dismissSnackProgress
import br.com.luan2.lgutilsk.utils.showStatusError
import br.com.luan2.lgutilsk.utils.showStatusMessage
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

class CustomPizzaActivity : BaseActivity(), CustomPizzaActivityContract.View, OnIngredienteClick, CartActivityContract.Interactor.CartSaveItem {

    val presenter: CustomPizzaActivityPresenter by inject()

    lateinit var snackbar: Snackbar
    lateinit var adapter: IngredienteAdapter
    lateinit var pizza: Pizza
    lateinit var ingredients: List<Ingredients>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_details)

        this.pizza = Pizza()

        presenter.provideView(pizza,this, this)

    }


    override fun bindView() {
        toolbar.title = pizza.name
        setSupportActionBar(toolbar)

        listIngredientes.apply {
            layoutManager = LinearLayoutManager(this@CustomPizzaActivity)
            setHasFixedSize(true)
        }

        Glide.with(this).load(R.drawable.place_holder).into(expandedImage)

        addToCart.setOnClickListener {
            presenter.createPizza(pizza,ingredients)
        }

        presenter.getIngredientes()
    }

    override fun onPizzaChanged(pizza: Pizza) {
        this.pizza = pizza
        onPizzaShow(adapter.items)
    }

    override fun onError(error: String) {
        showStatusError(error,R.color.red)
    }


    override fun showSuccess(ingredients: List<Ingredients>) {

        onPizzaShow(ingredients)
    }

    override fun onPizzaCreated(pizza: Pizza) {
        presenter.putCart(pizza,this)
    }

    override fun showProgress(message: String?) {
        snackbar = createSnackProgress(message!!)
        snackbar.show()
    }

    override fun hideProgress() = snackbar.dismissSnackProgress(this@CustomPizzaActivity)


    override fun onIngrediente(ingredients: Ingredients) {
        presenter.manageIngredient(ingredients)
    }

    override fun onCartItemSave() {
        showStatusMessage("Add com sucesso",R.color.colorAccent)
    }

    override fun onCartItemError(error: String) {
        showStatusError(error,R.color.red)
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

        this.ingredients = ingredients
    }
}
