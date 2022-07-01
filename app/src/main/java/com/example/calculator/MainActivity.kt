package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.text.Editable
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()
{
    private var canAddOperation = false
    private var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       }

    fun numberAction(view: View)
    {
        if(view is Button)
        {
            if(view.text == ".")
            {
                if(canAddDecimal)
                    workingTextView.append(view.text)

                canAddDecimal = false
            }
            else
                workingTextView.append(view.text)

            canAddOperation = true
        }
    }

    fun operationAction(view: View)
    {
        if(view is Button && canAddOperation)
        {
            workingTextView.append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun allClear(view: View)
    {
        workingTextView.text = ""
        resultsTextView.text = ""
    }

    fun delAction(view: View)
    {
        val length = workingTextView.length()
        if(length > 0)
            workingTextView.text = workingTextView.text.subSequence(0, length - 1)
    }

    fun equalsAction(view: View)
    {
        resultsTextView.text = calculateResults()
        resultsTextView.text = calculateResults()
    }

    private fun calculateResults(): String
    {
        val digitsOperators = digitsOperators()

        if(digitsOperators.isEmpty())

            return ""

        val timesDivision = timesDivisionCalculate(digitsOperators)

        if(timesDivision.isEmpty())

            return ""

        val result = addSubtractCalculate(timesDivision)

        return result.toString()
    }

    private fun addSubtractCalculate(oldList: MutableList<Any>): Float
    {
        var result = oldList[0] as Float

        for(i in oldList.indices)
        {
            if(oldList[i] is Char && i != oldList.lastIndex)
            {
                val operator = oldList[i]
                val nextDigit = oldList[i + 1] as Float
                if (operator == '+')
                    result += nextDigit
                if (operator == '-')
                    result -= nextDigit
            }
        }

        return result
    }

    private fun timesDivisionCalculate(oldList: MutableList<Any>): MutableList<Any>
    {
        var list = oldList
        while (list.contains('x') || list.contains('/'))
        {
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(oldList: MutableList<Any>): MutableList<Any>
    {
        val newList = mutableListOf<Any>()
        var restartIndex = oldList.size

        for(i in oldList.indices)
        {
            if(oldList[i] is Char && i != oldList.lastIndex && i < restartIndex)
            {
                val operator = oldList[i]
                val prevDigit = oldList[i - 1] as Float
                val nextDigit = oldList[i + 1] as Float
                when(operator)
                {
                    'x' ->
                    {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' ->
                    {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else ->
                    {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }

            if(i > restartIndex)
                newList.add(oldList[i])
        }

        return newList
    }

    private fun digitsOperators(): MutableList<Any>
    {
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for(character in workingTextView.text)
        {
            if(character.isDigit() || character == '.')
                currentDigit += character
            else
            {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(character)
            }
        }

        if(currentDigit != "")
            list.add(currentDigit.toFloat())

        return list
    }

}