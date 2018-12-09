
package br.com.luan2.nennospizza.view.activities.cart

import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.luan2.lgutilsk.utils.createSnackProgress
import br.com.luan2.lgutilsk.utils.dismissSnackProgress
import br.com.luan2.lgutilsk.utils.showError
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.ItemCart
import br.com.luan2.nennospizza.view.activities.BaseActivity
import br.com.luan2.nennospizza.view.adapters.cart.OnCartItem
import com.example.gitapi.adapter.CartAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_cart.*
import org.koin.android.ext.android.inject

class CartActivity : BaseActivity(), CartActivityContract.View, OnCartItem {

    val presenter : CartActivityPresenter by inject()

    lateinit var snackbar: Snackbar
    lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        presenter.provideView(this,this)

    }

    override fun bindView() {
        setSupportActionBar(toolbar)

        cartList.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            setHasFixedSize(true)
        }

        presenter.getCart()
    }

    override fun onError(error: String) {
        showError(error)
    }

    override fun showSuccess(cart: Cart) {
        val nCart = cart.deserialize()
        adapter = CartAdapter(nCart.items,this,this).also {
            cartList.adapter = it
        }
    }



    override fun showProgress(message: String?) {
        snackbar = createSnackProgress(message!!)
        snackbar.show()
    }

    override fun hideProgress() {
        snackbar.dismissSnackProgress(this@CartActivity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cart,menu)
        return true
    }

    override fun onItemDelete(itemCart: ItemCart) {

    }
}
