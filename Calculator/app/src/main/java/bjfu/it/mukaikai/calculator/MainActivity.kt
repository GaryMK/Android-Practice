package bjfu.it.mukaikai.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private var firstNumber = ""
    private var nextNumber = ""
    private var flag = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        equal.setOnClickListener(this)
        CE.setOnClickListener(this)
        division.setOnClickListener(this)
        multiplication.setOnClickListener(this)
        C.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        plus.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        subtraction.setOnClickListener(this)
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        radical.setOnClickListener(this)
        zero.setOnClickListener(this)
        point.setOnClickListener(this)
        equal.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        doClick("" + (v as Button).text)
    }

    private fun doClick(value:String) {
        when (value) {
            "+","-","×","÷" -> {
                if (flag?.isEmpty()) {
                    if (firstNumber?.isNotEmpty() && nextNumber?.isEmpty()) {
                        flag = value
                    } else if (firstNumber?.isNotEmpty() && nextNumber?.isNotEmpty()) {
                        flag = value
                        doCount()
                    }
                } else {
                    if (flag?.equals("√￣") && (value?.equals("-") || value?.equals("+"))) {
                        nextNumber += value
                    } else {
                        Toast.makeText(this, "等式错误", Toast.LENGTH_LONG).show()
                    }
                }

            }
            "√￣" -> {
                flag = value
            }
            "﹦" -> {
                if (firstNumber?.isNotEmpty() && nextNumber?.isNotEmpty()) {
                    doCount()
                    flag = ""
                } else if (nextNumber?.isNotEmpty() && flag?.equals("√￣")) {
                    doCount()
                    flag= ""
                } else if (firstNumber?.isNotEmpty() && nextNumber?.isEmpty()) {
                    doCount()
                    flag = ""
                }
                return
            }
            "C" -> {
                firstNumber = ""
                nextNumber = ""
                calculationResult.text = ""
                flag = ""
            }
            "CE" -> {
                if (nextNumber?.isNotEmpty()) {
                    nextNumber = nextNumber.substring(0, nextNumber.length - 1)
                } else if (flag?.isNotEmpty()) {
                    flag = ""
                } else if (firstNumber?.isNotEmpty()) {
                    firstNumber = firstNumber.substring(0,firstNumber.length - 1)
                }
            }
            else -> {
                if (flag?.isNotEmpty()) {
                    if (nextNumber?.isEmpty() && value?.equals("0") && flag.equals("÷")) {
                        nextNumber += value
                        Toast.makeText(this,"除数不能为0", Toast.LENGTH_LONG).show()
                    } else {
                        nextNumber += value
                        if (flag.equals("√￣") && nextNumber.toDouble() < 0) {
                            Toast.makeText(this, "开根号的数值不能小于0", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    firstNumber += value
                }
            }
        }
        expression.text = firstNumber + flag + nextNumber
    }

    private fun doCount() {
        var result = 0.0
        when (flag) {
            "+" -> result = firstNumber.toDouble() + nextNumber.toDouble()
            "-" -> result = firstNumber.toDouble() - nextNumber.toDouble()
            "×" -> result = firstNumber.toDouble() * nextNumber.toDouble()
            "÷" -> result = firstNumber.toDouble()/nextNumber.toDouble()
            "√￣" -> result = sqrt(nextNumber.toDouble())
            "=" -> result = firstNumber.toDouble()
        }
        firstNumber = result.toString()
        nextNumber = ""
        calculationResult.text = "=" + result.toString()
    }
}
