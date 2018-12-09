package br.com.luan2.nennospizza.view.activities

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.luan2.lgutilsk.utils.startActivity
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.view.activities.drinks.DrinksActivity

open class BaseActivity: AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
            R.id.add_more ->  startActivity(IngredientsActivity())
            R.id.to_drink -> startActivity(DrinksActivity())
            else -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}