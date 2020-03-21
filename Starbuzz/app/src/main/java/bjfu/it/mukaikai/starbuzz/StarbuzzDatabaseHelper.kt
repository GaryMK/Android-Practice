package bjfu.it.mukaikai.starbuzz

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class StarbuzzDatabaseHelper(context: Context?) :
    SQLiteOpenHelper(
        context,
        DB_NAME,
        null,
        DB_VERSION
    ) {

    companion object {
        private const val DB_NAME = "starbuzz.db"
        private const val DB_VERSION = 3
    }

    override fun onCreate(db: SQLiteDatabase) {
        db?.execSQL("CREATE TABLE DRINK (_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "NAME TEXT,"
            + "DESCRIPTION TEXT,"
            + "IMAGE_RESOURCE_ID INTEGER);"
        )

        insertDrink(db, "Latte", "Espresso and steamed milk", R.drawable.latte)
        insertDrink(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam", R.drawable.cappuccino)
        insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion <= 1) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;")
        }
        if (oldVersion <= 2) {
            var latteDesc = ContentValues()
            latteDesc.put("DESCRIPTION", "Tasty")
            db.update(
                "DRINK", latteDesc,
                "NAME = ?", arrayOf("Latte")
            )
        }
    }

    private fun insertDrink(db: SQLiteDatabase, name: String, description: String, resourceId: Int) {
        var drinkValues = ContentValues()
        drinkValues.put("NAME", name)
        drinkValues.put("DESCRIPTION", description)
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId)
        var result = db.insert("DRINK", null, drinkValues)
        Log.d("sqlite", "insert$name,_id:$result")
    }
}