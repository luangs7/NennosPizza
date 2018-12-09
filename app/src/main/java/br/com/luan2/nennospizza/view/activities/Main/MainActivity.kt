package br.com.luan2.nennospizza.view.activities.main

import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.luan2.lgutilsk.utils.createSnackProgress
import br.com.luan2.lgutilsk.utils.dismissSnackProgress
import br.com.luan2.lgutilsk.utils.showStatusError
import br.com.luan2.lgutilsk.utils.showStatusMessage
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.Pizza
import br.com.luan2.nennospizza.view.activities.BaseActivity
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract
import com.example.gitapi.adapter.OnClickPizza
import com.example.gitapi.adapter.PizzaAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : BaseActivity(), MainActivityContract.View, OnClickPizza {

    val presenter: MainActivityPresenter by inject()

    lateinit var adapter: PizzaAdapter
    lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.provideView(this, this@MainActivity)

    }

    override fun bindView() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { presenter.goToCart() }

        list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        presenter.getPizzas()
    }

    override fun onError(error: String) {
        showStatusError(error,R.color.red)
    }

    override fun showSuccess(pizzas: List<Pizza>) {
        adapter = PizzaAdapter(pizzas, this, this)
        list.adapter = adapter
    }

    override fun hideKeyboard() {
    }

    override fun showProgress(message: String?) {
        snackbar = createSnackProgress(message!!)
        snackbar.show()
    }

    override fun hideProgress() = snackbar.dismissSnackProgress(this@MainActivity)


    override fun onItemToCart(pizza: Pizza) {
        presenter.putCart(pizza, object : CartActivityContract.Interactor.CartSaveItem {
            override fun onCartItemSave() {
                showStatusMessage("Added to cart",R.color.colorAccent)
            }

            override fun onCartItemError(error: String) {
                showStatusError(error,R.color.red)
            }
        })
    }

    override fun onItemDetails(pizza: Pizza) {
       presenter.openDetails(pizza)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }
}

