package bjfu.it.mukaikai.starbuzz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drink_category.*

class DrinkCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)
        val listAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, Drink.Companion.drinks
        )
        list_drinks.adapter = listAdapter
        (list_drinks as ListView).onItemClickListener =
            object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    var intent = Intent(this@DrinkCategoryActivity, DrinkActivity::class.java)
                    intent.putExtra(DrinkActivity.EXTRA_DRINKID, id.toInt())
                    startActivity(intent)
                }
            }
    }
}