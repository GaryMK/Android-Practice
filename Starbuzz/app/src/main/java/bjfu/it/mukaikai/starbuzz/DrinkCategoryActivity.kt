package bjfu.it.mukaikai.starbuzz

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drink_category.*

class DrinkCategoryActivity : AppCompatActivity() {

    private var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)
/**        val listAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, Drink.Companion.drinks
        )
        list_drinks.adapter = listAdapter
 **/

        val starbuzzDatabaseHelp: SQLiteOpenHelper = StarbuzzDatabaseHelper(this)

        try {
            val db: SQLiteDatabase = starbuzzDatabaseHelp.readableDatabase
            cursor = db.query(
                "DRINK",
                null,
                null, null, null, null, null
            )
            val listAdapter =
                SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    cursor, arrayOf("NAME"), intArrayOf(android.R.id.text1),
                    0
                )
            list_drinks.adapter = listAdapter
        } catch (e: SQLiteException) {
            Log.e("sqlite", e.message)
            val toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT)
            toast.show()
        }
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

    override fun onDestroy() {
        super.onDestroy()
        cursor?.close()
    }
}