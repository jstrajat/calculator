package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Calculator : AppCompatActivity() {
    private var currentValue = 0.0
    private var firstVal: Double? = null
    private var operator = ""
    private var isNewCalculation = true
    private var isEmpty = true
    private var finalVal: Double? = null

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        val input = findViewById<TextView>(R.id.input)
        val output = findViewById<TextView>(R.id.result)
        val clearbtn = findViewById<Button>(R.id.clear_btn)
        val equal = findViewById<Button>(R.id.btn_equal_to)

        val historyRecyclerView = findViewById<RecyclerView>(R.id.historyView)
        val PreviousCalculationHistory = findViewById<Button>(R.id.history)





        val nobtn: List<Button> = listOf(
            findViewById(R.id.no0),
            findViewById(R.id.no1),
            findViewById(R.id.no2),
            findViewById(R.id.no3),
            findViewById(R.id.no4),
            findViewById(R.id.no5),
            findViewById(R.id.no6),
            findViewById(R.id.no7),
            findViewById(R.id.no8),
            findViewById(R.id.no9),
        )

        val operatorButtons: List<Button> = listOf(
            findViewById(R.id.PlusButton),
            findViewById(R.id.subbtn),
            findViewById(R.id.multiplyBtn),
            findViewById(R.id.divide_btn),
        )

        nobtn.forEach { button ->
            button.setOnClickListener {
                output.text = ""
                onNumberClick(button.text.toString())
                if (firstVal != null) {
                    input.text = "$firstVal$operator$currentValue"
                } else {
                    finalVal = null
                    if (isEmpty) {
                        input.text = currentValue.toString()
                        isEmpty = false
                    } else {
                        input.text = "$currentValue"
                    }
                }
            }
        }



        operatorButtons.forEach { button ->
            button.setOnClickListener {
                output.text = ""
                if (firstVal == null && finalVal == null) {
                    firstVal = currentValue
                    onOperatorClick(button.text.toString())
                    input.text = "$firstVal ${button.text}"
                } else {
                    firstVal = finalVal
                    onOperatorClick(button.text.toString())
                    input.text = "$firstVal ${button.text}"
                }
            }
        }

        clearbtn.setOnClickListener {
            clear()
            input.text = ""
            output.text = ""
        }

        PreviousCalculationHistory.setOnClickListener {
            // Create an Intent to navigate to SecondActivity
            val intent = Intent(this, HistoryViewActivity::class.java)
            startActivity(intent)
        }


        equal.setOnClickListener {

            calculateResult(firstVal ?: 0.0, currentValue)
            finalVal = currentValue
            output.text = currentValue.toString()
            firstVal = null
            currentValue = 0.0

            val replyInput = Intent()
            val replyOutput = Intent()
            if (TextUtils.isEmpty(input.text) && TextUtils.isEmpty(output.text)) {
                setResult(Activity.RESULT_CANCELED, replyInput)
//                setResult(Activity.RESULT_CANCELED, replyOutput)
            } else {
                val userInput = input.text.toString()
                val finalResult = output.text.toString()
                replyInput.putExtra(EXTRA_REPLY, userInput)
//                replyOutput.putExtra(EXTRA_REPLY, finalResult)
                setResult(Activity.RESULT_OK, replyInput)
//                setResult(Activity.RESULT_OK, replyOutput)
            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.historyResult.REPLY"
    }


    private fun onNumberClick(number: String) {
        if (isNewCalculation) {
            currentValue = number.toDouble()
            isNewCalculation = false
        } else {
            currentValue = currentValue * 10 + number.toDouble()
        }
    }

    private fun onOperatorClick(op: String) {
        operator = op
        isNewCalculation = true


    }

    private fun calculateResult(n1:Double , n2 : Double) {
        when (operator.lowercase()) {
            "+" -> currentValue = n1 + n2
            "-" -> currentValue = n1 - n2
            "x" -> currentValue = n1 * n2
            "/" -> currentValue = n1 / n2
            "%" -> currentValue = n1 % n2
        }
    }

    private fun clear() {
        currentValue = 0.0
        firstVal = null
        finalVal = null
        operator = ""
        isNewCalculation = true
        isEmpty = true
    }
}
