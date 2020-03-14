package bjfu.it.mukaikai.starbuzz

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import bjfu.it.mukaikai.starbuzz.Drink.Companion.drinks
import kotlinx.android.synthetic.main.activity_drink.*

class DrinkActivity : AppCompatActivity() {
    companion object {
        val EXTRA_DRINKID:String = "drinkId"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)
        val drinkId = intent.getIntExtra(EXTRA_DRINKID, 0)
        val drink = drinks[drinkId]

        photo.setImageResource(drink.imageResourceId)
        photo.contentDescription = drink.name

        name.text = drink.name
        description.text = drink.description
    }
}
