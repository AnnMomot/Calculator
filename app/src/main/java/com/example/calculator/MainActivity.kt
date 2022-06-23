package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.text.Editable
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var canAddOperation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       }

    fun numberAction(view: View){
        if(view is Button){
            input.append(view.text)
            canAddOperation = true
        }

    }

    fun operationAction(view: View){
        if(view is Button && canAddOperation){
            input.append(view.text)
            canAddOperation = false
        }

    }

    fun AllClearActions(view: View){
        input.text = ""
        output.text = ""
    }
    fun backSpaceAction(view: View){
        val length = input.length()
        if(length > 0)
            input.text = input.text.subSequence(0, length - 1)
    }
}

