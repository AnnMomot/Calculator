package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
    //private var strNumber: String = ""
    private var strNumber = StringBuilder()
    private lateinit var workingTextView: TextView
    private lateinit var numberButtons: Array<Button>
    private lateinit var operatorButtons: List<Button>
    private var operator:Operator = Operator.NONE
    private var isOperatorCliked: Boolean = false
    private var operand1: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workingTextView = findViewById(R.id.workingTextView)
        val workingTextView: TextView = findViewById(R.id.workingTextView)

        numberButtons = arrayOf(number9,number8,number7,number6, number5,number4,number3,number2,number1,number0)
        for (i in numberButtons){
            i.setOnClickListener {numberButtonClick(i)}
        }
        operatorButtons = listOf(DIV,MUL,SUB,ADD,CLEAR)
        for (i in operatorButtons){i.setOnClickListener {operatorButtonClick(i)}}
        equal.setOnClickListener {buttonEqualClick()}
        CLEAR.setOnClickListener {buttonClearClick()}
    }
    private fun buttonClearClick(){
        val result = when(operator){
            Operator.CLEAR -> 0
            else -> 0
        }
        strNumber.clear()
        strNumber.append(result.toString())
        workingTextView.text = strNumber
        isOperatorCliked = true
    }
    private fun buttonEqualClick() {
        val operand2 = strNumber.toString().toInt()
        val result = when(operator){
            Operator.ADD -> operand1 + operand2
            Operator.SUB -> operand1 - operand2
            Operator.MUL -> operand1 * operand2
            Operator.DIV -> operand1 / operand2

            else -> 0
        }
        strNumber.clear()
        strNumber.append(result.toString())
        workingTextView.text = strNumber
        isOperatorCliked = true
    }
    private fun operatorButtonClick(btn: Button) {
        if(btn.text == "+") operator = Operator.ADD
        else if(btn.text == "-") operator = Operator.SUB
        else if(btn.text == "*") operator = Operator.MUL
        else if(btn.text == "/") operator = Operator.DIV
        else if(btn.text == "C") operator = Operator.CLEAR
        else operator = Operator.NONE
        workingTextView.text = ""
        isOperatorCliked = true
    }
    private fun numberButtonClick(btn: Button) {
        if(isOperatorCliked){
            operand1 = strNumber.toString().toInt()
            strNumber.clear()
            isOperatorCliked = false
        }
        strNumber.append(btn.text)
        workingTextView.text = strNumber
    }
}
enum class Operator {ADD,SUB,MUL,DIV,NONE,CLEAR}