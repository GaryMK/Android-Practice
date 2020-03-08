package bjfu.it.mukaikai.messager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_message.*


class CreateMessageActivity : AppCompatActivity(),View.OnClickListener {
    companion object {
        const val MESSAGE_KEY:String = "bjfu.it.mukaikai.messager"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_message)
        var button:Button = findViewById(R.id.button)
        var editText = findViewById<EditText>(R.id.input)
        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent()
                var message:String = editText.getText().toString()
                intent.setClass(this@CreateMessageActivity, ReceiveMessageActivity::class.java)
                intent.putExtra(MESSAGE_KEY, message)
                startActivity(intent)
            }
        })
        other.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v?.id) {
            R.id.other -> {
                var editText = findViewById<EditText>(R.id.input)
                var message:String = editText.getText().toString()
                var intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT, message)
//                startActivity(intent)
                var chooserTitle:String = getString(R.string.choser)
                var chosenIntent:Intent = Intent.createChooser(intent, chooserTitle)
                startActivity(chosenIntent)
            }
        }
    }
}
