package bjfu.it.mukaikai.starbuzz

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drink.*

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
            var cursor: Cursor = db.query(
                "DRINK",
                arrayOf("NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID","FAVORITE"),
                "_id = ?",
                arrayOf(Integer.toString(drinkId)),
                null,
                null,
                null
            )
            if (cursor.moveToFirst()) {
                var nameText: String = cursor.getString(0)
                var descriptionText: String = cursor.getString(1)
                var photoId: Int = cursor.getInt(2)
                var isFavorite: Boolean = cursor.getInt(3) == 1
                name.text = nameText
                description.text = descriptionText
                photo.setImageResource(photoId)
                photo.contentDescription = nameText
                favorite.isChecked = isFavorite
            }
        } catch (e: SQLiteException) {
            Log.e("sqlite", e.message)
            val toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT)
            toast.show()
        }
        favorite.setOnClickListener { v ->
            var drinkValues = ContentValues()
            drinkValues.put("FAVORITE", favorite.isChecked);
            try {
                var db = starbuzzDatabaseHelp.writableDatabase
                var row = db.update("DRINK",
                    drinkValues,
                    "_id = ?",
                    arrayOf(Integer.toString(drinkId)))
                Log.d("sqlite", "update row" + row)
            } catch (e: SQLiteException) {
                Log.e("sqlite", e.message)
                var toast: Toast = Toast.makeText(this, "Database unwritable", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }
}
