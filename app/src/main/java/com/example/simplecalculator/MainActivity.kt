package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvResult: TextView? = null
    var lastNumber: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.Result)
    }

    fun onDigit(view: View) {
        tvResult?.append((view as Button).text)
        lastNumber=true
        lastDot=false
    }
    fun onClear(view: View){
        tvResult?.text=""
    }

    fun onDecimalPoint(view: View) {
        if (lastNumber && !lastDot){
            tvResult?.append(".")
            lastDot=true
            lastNumber=false
        }
    }
    fun onOperator(view: View){
    tvResult?.text?.let {
        if(lastNumber && !isOperatorAdded(it.toString())){
            tvResult?.append((view as Button).text)
            lastDot = false
            lastNumber = false
        }
    }
    }

    fun onEquals(view: View){
        if(lastNumber){
            var tvValue = tvResult?.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){

                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    tvResult?.text = (one.toDouble()-two.toDouble()).toString()
                }else if(tvValue.contains("+")){

                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    tvResult?.text = (one.toDouble()+two.toDouble()).toString()
                }else if(tvValue.contains("/")){

                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    tvResult?.text = (one.toDouble()/two.toDouble()).toString()
                }else if(tvValue.contains("*")){

                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    tvResult?.text = (one.toDouble()*two.toDouble()).toString()
                }
            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }
    }


    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    ||value.contains("+")
                    ||value.contains("*")
                    ||value.contains("-")
        }
    }
}
