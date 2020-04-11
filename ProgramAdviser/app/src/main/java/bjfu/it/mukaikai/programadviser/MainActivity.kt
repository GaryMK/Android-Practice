package bjfu.it.mukaikai.programadviser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.find_language)
        val spinner = findViewById<Spinner>(R.id.feature)
        val textView = findViewById<TextView>(R.id.language)
        val expert = PragramExpert()
        button.setOnClickListener {textView.text = expert.getLanguage(spinner.selectedItem.toString())}
    }
}
