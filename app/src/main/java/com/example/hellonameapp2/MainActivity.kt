package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hellonameapp2.R

class MainActivity : AppCompatActivity() {

    private lateinit var txtDisplay: TextView
    private var currentInput = StringBuilder()
    private var currentOperator: String? = null
    private var firstOperand: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtDisplay = findViewById(R.id.txtDisplay)

        // Number buttons
        findViewById<Button>(R.id.btnClick0).setOnClickListener { appendNumber("1") }
        findViewById<Button>(R.id.btnClick1).setOnClickListener { appendNumber("2") }
        findViewById<Button>(R.id.btnClick2).setOnClickListener { appendNumber("3") }
        findViewById<Button>(R.id.btnClick3).setOnClickListener { appendNumber("4") }
        findViewById<Button>(R.id.btnClick4).setOnClickListener { appendNumber("5") }
        findViewById<Button>(R.id.btnClick5).setOnClickListener { appendNumber("6") }
        findViewById<Button>(R.id.btnClick6).setOnClickListener { appendNumber("7") }
        findViewById<Button>(R.id.btnClick7).setOnClickListener { appendNumber("8") }
        findViewById<Button>(R.id.btnClick8).setOnClickListener { appendNumber("9") }
        findViewById<Button>(R.id.btnClick10).setOnClickListener { appendNumber("0") }

        // Operator buttons
        findViewById<Button>(R.id.btnClick12).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnClick13).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnClick14).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnClick20).setOnClickListener { setOperator("/") }

        // Equals button
        findViewById<Button>(R.id.btnClick15).setOnClickListener { calculateResult() }

        // Clear button
        findViewById<Button>(R.id.btnClick16).setOnClickListener { clearCalculator() }
    }

    private fun appendNumber(number: String) {
        currentInput.append(number)
        updateDisplay()
    }

    private fun setOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toString().toDouble()
            currentOperator = operator
            currentInput.clear()
            updateDisplay()
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && firstOperand != null && currentOperator != null) {
            val secondOperand = currentInput.toString().toDouble()
            val result = when (currentOperator) {
                "+" -> firstOperand!! + secondOperand
                "-" -> firstOperand!! - secondOperand
                "*" -> firstOperand!! * secondOperand
                "/" -> if (secondOperand != 0.0) firstOperand!! / secondOperand else Double.NaN
                else -> Double.NaN
            }
            currentInput.clear()
            currentInput.append(result)
            updateDisplay()
            firstOperand = null
            currentOperator = null
        }
    }

    private fun clearCalculator() {
        currentInput.clear()
        firstOperand = null
        currentOperator = null
        updateDisplay()
    }

    private fun updateDisplay() {
        txtDisplay.text = if (currentInput.isEmpty()) "0" else currentInput.toString()
    }
}