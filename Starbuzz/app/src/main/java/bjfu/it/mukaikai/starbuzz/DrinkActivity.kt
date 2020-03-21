package bjfu.it.mukaikai.starbuzz

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DrinkActivity : AppCompatActivity() {
    companion object {
        val EXTRA_DRINKID:String = "drinkId"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)
        val drinkId = intent.getIntExtra(EXTRA_DRINKID, 0)
//        val drink = drinks[drinkId]
//
//        photo.setImageResource(drink.imageResourceId)
//        photo.contentDescription = drink.name
//
//        name.text = drink.name
//        description.text = drink.description
        val starbuzzDatabaseHelp: SQLiteOpenHelper = StarbuzzDatabaseHelper(this)
        try {
            var db : SQLiteDatabase = starbuzzDatabaseHelp.readableDatabase
            Log.d("db", db.toString())
        } catch (e: SQLiteException) {
            Log.e("sqlite", e.message)
            val toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}
