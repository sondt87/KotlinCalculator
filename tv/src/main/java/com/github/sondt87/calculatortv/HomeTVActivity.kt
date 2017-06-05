package com.github.sondt87.calculatortv

import android.app.Activity
import android.content.res.Resources
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.graphics.drawable.VectorDrawableCompat
import android.view.View
import android.widget.TextView
import com.github.sondt87.calculator.Calculator
import com.github.sondt87.calculator.CalculatorImpl
import com.github.sondt87.calculator.operation.Key
import com.github.sondt87.kotlincalculatortv.R
import com.github.sondt87.kotlincalculatortv.databinding.ActivityHomeBinding

import utils.string.doubleToString

class HomeTVActivity : Activity(), Calculator {

    val mCal = CalculatorImpl(this)
    var mResult: TextView? = null
    var mFormula: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
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
