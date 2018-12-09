package br.com.luan2.nennospizza.view.activities.checkout

import android.os.Bundle
import br.com.luan2.lgutilsk.utils.showStatusMessage
import br.com.luan2.lgutilsk.utils.splashOpen
import br.com.luan2.lgutilsk.utils.startActivity
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.view.activities.BaseActivity
import br.com.luan2.nennospizza.view.activities.main.MainActivity

class CheckoutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        showStatusMessage("Checkout realizado com sucesso.",R.color.colorAccent)

        splashOpen {
            startActivity(MainActivity())
        }
    }
}
