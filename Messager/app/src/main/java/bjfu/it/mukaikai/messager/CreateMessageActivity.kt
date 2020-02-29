package bjfu.it.mukaikai.messager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class CreateMessageActivity : AppCompatActivity() {
    companion object {
        val MESSAGE_KEY:String = "bjfu.it.mukaikai.messager"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_message)
        val button:Button = findViewById(R.id.button)
        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var editText = findViewById<EditText>(R.id.input)
                var message:String = editText.getText().toString()
                val intent = Intent()
                intent.setClass(this@CreateMessageActivity, ReceiveMessageActivity::class.java)
                intent.putExtra(MESSAGE_KEY,message)
                startActivity(intent)
            }
        })
    }
}
