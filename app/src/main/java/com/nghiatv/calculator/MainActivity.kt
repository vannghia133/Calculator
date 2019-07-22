package com.nghiatv.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            text_clear.id -> {
                text_expression.text = ""
                text_result.text = ""
            }

            text_back.id -> {
                val string = text_expression.text.toString()
                if (string.isNotEmpty()) {
                    text_expression.text = string.substring(0, string.length - 1)
                }
                text_result.text = ""
            }

            text_equal.id -> {
                try {
                    val expression = ExpressionBuilder(text_expression.text.toString()).build()
                    val doubleResult = expression.evaluate()
                    val longResult = doubleResult.toLong()
                    if (doubleResult == longResult.toDouble()) {
                        text_result.text = longResult.toString()
                    } else {
                        text_result.text = doubleResult.toString()
                    }
                } catch (e: Exception) {
                    Log.d(TAG, " message: " + e.message)
                }
            }
        }
    }

    fun initialize() {
        // Numbers
        text_zero.setOnClickListener { appendOnExpression(Constants.ZERO, true) }
        text_one.setOnClickListener { appendOnExpression(Constants.ONE, true) }
        text_two.setOnClickListener { appendOnExpression(Constants.TWO, true) }
        text_three.setOnClickListener { appendOnExpression(Constants.THREE, true) }
        text_four.setOnClickListener { appendOnExpression(Constants.FOUR, true) }
        text_five.setOnClickListener { appendOnExpression(Constants.FIVE, true) }
        text_six.setOnClickListener { appendOnExpression(Constants.SIX, true) }
        text_seven.setOnClickListener { appendOnExpression(Constants.SEVEN, true) }
        text_eight.setOnClickListener { appendOnExpression(Constants.EIGHT, true) }
        text_nine.setOnClickListener { appendOnExpression(Constants.NINE, true) }
        text_dot.setOnClickListener { appendOnExpression(Constants.DOT, true) }

        // Operators
        text_plus.setOnClickListener { appendOnExpression(Constants.PLUS, false) }
        text_minus.setOnClickListener { appendOnExpression(Constants.MINUS, false) }
        text_times.setOnClickListener { appendOnExpression(Constants.TIMES, false) }
        text_div.setOnClickListener { appendOnExpression(Constants.DIV, false) }
        text_open.setOnClickListener { appendOnExpression(Constants.OPEN, false) }
        text_close.setOnClickListener { appendOnExpression(Constants.CLOSE, false) }

        // Set event onclick
        text_clear.setOnClickListener(this)
        text_back.setOnClickListener(this)
        text_equal.setOnClickListener(this)
    }

    fun appendOnExpression(string: String, canClear: Boolean) {
        if (text_result.text.isNotEmpty()) {
            text_expression.text = ""
        }

        if (canClear) {
            text_result.text = ""
            text_expression.append(string)
        } else {
            text_expression.append(text_result.text)
            text_expression.append(string)
            text_result.text = ""
        }
    }
}
