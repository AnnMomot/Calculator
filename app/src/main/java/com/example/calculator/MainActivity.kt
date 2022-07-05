package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 1. Handle number buttons
 * 2. Addition operation
 * 3. Subtract operation
 */

class MainActivity : AppCompatActivity() {
    private var strNumber: String = ""
    private lateinit var workingTextView: TextView
    private lateinit var operatorButtons: List<Button>
    private var operator:Operator = Operator.NONE
    private var isOperatorCliked: Boolean = false
    private var operand1: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workingTextView = findViewById(R.id.workingTextView)

        val number9:Button = findViewById(R.id.number9)
        number9.setOnClickListener {
            strNumber += "9"
            workingTextView.text = strNumber
        }
        val number8:Button = findViewById(R.id.number8)
        number8.setOnClickListener {
            strNumber += "8"
            workingTextView.text = strNumber
        }
        val number7:Button = findViewById(R.id.number7)
        number7.setOnClickListener {
            strNumber += "7"
            workingTextView.text = strNumber
        }
        val number6:Button = findViewById(R.id.number6)
        number6.setOnClickListener {
            strNumber += "6"
            workingTextView.text = strNumber
        }
        val number5:Button = findViewById(R.id.number5)
        number5.setOnClickListener {
            strNumber += "5"
            workingTextView.text = strNumber
        }
        val number4:Button = findViewById(R.id.number4)
        number4.setOnClickListener {
            strNumber += "4"
            workingTextView.text = strNumber
        }
        val number3:Button = findViewById(R.id.number3)
        number3.setOnClickListener {
            strNumber += "3"
            workingTextView.text = strNumber
        }
        val number2:Button = findViewById(R.id.number2)
        number2.setOnClickListener {
            strNumber += "2"
            workingTextView.text = strNumber
        }
        val number1:Button = findViewById(R.id.number1)
        number1.setOnClickListener {
            strNumber += "1"
            workingTextView.text = strNumber
        }
        val number0:Button = findViewById(R.id.number0)
        number0.setOnClickListener {
            strNumber += "0"
            workingTextView.text = strNumber
        }

        operatorButtons = listOf(DIV,MUL,SUB,ADD)
        for (i in operatorButtons){i.setOnClickListener { operatorButtonClick(i) }}
        equal.setOnClickListener {buttonEqualClick()}
    }

    private fun buttonEqualClick() {
        val operand2 = strNumber.toString().toInt()
        val result:Int
        when(operator){
            Operator.ADD -> result = operand1 + operand2
            Operator.SUB -> result = operand1 - operand2
            Operator.MUL -> result = operand1 * operand2
            Operator.DIV -> result = operand1 / operand2
            else -> result = 0
        }
        resultsTextView.text = result.toString()
    }

    private fun operatorButtonClick(btn: Button) {
        if(btn.text == "+") operator = Operator.ADD
        else if(btn.text == "-") operator = Operator.SUB
        else if(btn.text == "*") operator = Operator.MUL
        else if(btn.text == "/") operator = Operator.DIV
        else operator = Operator.NONE
        workingTextView.text = ""
        isOperatorCliked = true
    }

    private fun numberButtonClick(btn:Button){
        if(isOperatorCliked){
            operand1 = strNumber.toString().toInt()
            isOperatorCliked = false
        }
    }
}

enum class Operator {ADD,SUB,MUL,DIV,NONE}