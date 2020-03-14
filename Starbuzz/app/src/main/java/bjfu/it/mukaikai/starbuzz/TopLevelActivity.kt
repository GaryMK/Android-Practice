package bjfu.it.mukaikai.starbuzz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_top_level.*

class TopLevelActivity : AppCompatActivity() {

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
    }
}
