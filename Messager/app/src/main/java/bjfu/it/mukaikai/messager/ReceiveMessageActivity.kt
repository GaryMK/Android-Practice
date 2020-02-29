package bjfu.it.mukaikai.messager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlin.math.log

class ReceiveMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_message)
        val intent = getIntent()
        var message = intent.getStringExtra(CreateMessageActivity.MESSAGE_KEY)
        val textView = findViewById<TextView>(R.id.output)
        textView.setText(message)
    }
}
