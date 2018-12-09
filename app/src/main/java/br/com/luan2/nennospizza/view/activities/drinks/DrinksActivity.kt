package br.com.luan2.nennospizza.view.activities.drinks

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.luan2.lgutilsk.utils.createSnackProgress
import br.com.luan2.lgutilsk.utils.dismissSnackProgress
import br.com.luan2.lgutilsk.utils.showStatusError
import br.com.luan2.lgutilsk.utils.showStatusMessage
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.view.activities.BaseActivity
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract
import com.example.gitapi.adapter.DrinkAdapter
import com.example.gitapi.adapter.OnClickDrink
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_drinks.*
import org.koin.android.ext.android.inject

class DrinksActivity : BaseActivity(), DrinksActivityContract.View, OnClickDrink {

    val presenter: DrinksActivityPresenter by inject()

    lateinit var adapter: DrinkAdapter
    lateinit var snackbar: Snackbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drinks)

        presenter.provideView(this,this@DrinksActivity)
    }

    override fun bindView() {
        setSupportActionBar(toolbar)

        drinkList.apply {
            layoutManager = LinearLayoutManager(this@DrinksActivity)
            setHasFixedSize(true)
        }

        presenter.getDrinks()
    }

    override fun onError(error: String) {
        showStatusError(error,R.color.red)
    }

    override fun showSuccess(drinks: List<Drinks>) {
        adapter = DrinkAdapter(drinks,this,this).also {
            drinkList.adapter = it
        }
    }



    override fun showProgress(message: String?) {
        snackbar = createSnackProgress(message!!)
        snackbar.show()
    }

    override fun hideProgress() = snackbar.dismissSnackProgress(this@DrinksActivity)

    override fun onDrinkToCart(drinks: Drinks) {
        presenter.putCart(drinks,object :CartActivityContract.Interactor.CartSaveItem{
            override fun onCartItemSave() {
                showStatusMessage("Added to cart",R.color.colorAccent)
            }

            override fun onCartItemError(error: String) {
                showStatusError(error,R.color.red)
            }
        })
    }
}
