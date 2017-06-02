package com.github.sondt87.calculator

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.github.sondt87.calculator.operation.Key
import com.github.sondt87.kotlincalculator.R.*
import com.github.sondt87.kotlincalculator.databinding.ActivityHomeBinding
import utils.string.doubleToString

class HomeActivity : AppCompatActivity(), Calculator {

    val mCal = CalculatorImpl(this)
    var mResult: TextView? = null
    var mFormula: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_home)

        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, layout.activity_home)
        binding.numpadClickedListener = View.OnClickListener { v: View? -> mCal.numpadClicked(v!!.id) }
        binding.operationClickedListener = View.OnClickListener { v: View? -> mCal.operationClicked(v!!.id) }
        binding.btnClear.setOnLongClickListener(View.OnLongClickListener { v: View? -> resetClicked() })

        mResult = binding.result
        mFormula = binding.formula

    }

    override fun setValue(value: String) {
        mResult?.text = value
    }

    override fun setValueDouble(value: Double) {
        mCal.setValue(doubleToString(value))
        mCal.lastKey = Key.DIGIT
    }

    override fun setFormula(value: String) {
        mFormula?.text = value
    }

    fun equalsClicked(v: View) {
        mCal.handleEquals()
    }

    fun clearClicked(v: View) {
        mCal.handleClear()
    }
    fun resetClicked() :Boolean {
        mCal.handleReset()
        return true
    }
}
