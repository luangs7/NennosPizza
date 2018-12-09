package br.com.luan2.nennospizza.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.luan2.lgutilsk.utils.showStatusError
import br.com.luan2.lgutilsk.utils.splashOpen
import br.com.luan2.lgutilsk.utils.startActivity
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.repositories.CartRepository
import br.com.luan2.nennospizza.view.activities.cart.CartActivityContract
import br.com.luan2.nennospizza.view.activities.main.MainActivity
import br.com.squarebits.ninky.data.dao.LocalDbImplement
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity(), CartActivityContract.Interactor.CarCacheInfo {

    private val cartRepository: CartRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        cartRepository.getCartList(this)
    }

    override fun onCartSuccess(cart: Cart?) {

        cart?.let {
            initApp(it)

        } ?: cartRepository.saveInitCart(object : CartActivityContract.Interactor.CartInit {
            override fun onCartInitSave(cart: Cart) {
                initApp(cart)
            }

            override fun onCartInitError(error: String) {
                showStatusError(error,R.color.red)
            }
        })

    }

    override fun onCartError(error: String) {
        showStatusError(error,R.color.red)
    }


    private fun initApp(cart: Cart) {
        LocalDbImplement<Cart>(this@SplashActivity).save(cart)
        splashOpen { startActivity(MainActivity()) }
    }
}
