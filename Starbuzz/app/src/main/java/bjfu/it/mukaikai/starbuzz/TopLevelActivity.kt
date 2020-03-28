package bjfu.it.mukaikai.starbuzz

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_top_level.*

class TopLevelActivity : AppCompatActivity() {

    private lateinit var favoriteCursor: Cursor
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_level)
        (list_options as ListView).onItemClickListener =
            object : OnItemClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {
                        val intent = Intent(
                            this@TopLevelActivity,
                            DrinkCategoryActivity::class.java
                        )
                        startActivity(intent)
                    }
                }
            }

        var starbuzzDatabaseHelper: SQLiteOpenHelper = StarbuzzDatabaseHelper(this)
        try {
            db = starbuzzDatabaseHelper.readableDatabase
            favoriteCursor = db.query("DRINK",
            arrayOf("_id", "NAME"),
            "FAVORITE = 1",
            null, null, null, null)
            var favoriteAdapter: CursorAdapter = SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                favoriteCursor,
                arrayOf("NAME"),
                intArrayOf(android.R.id.text1),
                0)
            list_favorite.adapter = favoriteAdapter
        } catch (e: SQLiteException) {
            Log.e("sqlite", e.message)
            val toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT)
            toast.show()
        }
        (list_favorite as ListView).onItemClickListener =
            object : OnItemClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {
                        var intent = Intent(this@TopLevelActivity,
                            DrinkActivity::class.java)
                        intent.putExtra(DrinkActivity.EXTRA_DRINKID, id.toInt())
                        startActivity(intent)
                    }
                }
            }
    }

    override fun onRestart() {
        super.onRestart()
        var newCursor: Cursor = db.query("DRINK",
        arrayOf("_id", "NAME"),
        "FAVORITE = 1",
        null, null, null, null)
        var adapte: CursorAdapter = list_favorite.adapter as CursorAdapter
        adapte.changeCursor(newCursor)
        favoriteCursor = newCursor
    }

    override fun onDestroy() {
        super.onDestroy()
        favoriteCursor.close()
        db.close()
    }
}
