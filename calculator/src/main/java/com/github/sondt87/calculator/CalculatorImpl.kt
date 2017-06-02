package com.github.sondt87.calculator

import com.github.sondt87.calculator.operation.OperationFactory
import com.github.sondt87.calculator.operation.Key
import utils.string.doubleToString
import utils.string.stringToDouble


class CalculatorImpl(val calculator: Calculator, value: String = "") {

    var baseValue = 0.0
    var secondValue = 0.0
    var isFirstOperation = true
    var resetValue = false
    var displayedValue = ""
    var displayedFormula = ""
    var lastKey = Key.EMPTY
    var lastOperation = Key.EMPTY

    fun resetValues() {
        baseValue = 0.0
        secondValue = 0.0
        isFirstOperation = true
        resetValue = false
        displayedValue = ""
        displayedFormula = ""
        lastKey = Key.EMPTY
        lastOperation = Key.EMPTY
    }

    fun setValue(value: String) {
        calculator.setValue(value)
        displayedValue = value
    }

    fun setFormular(value: String) {
        calculator.setFormula(value)
        displayedFormula = value
    }

    fun resetValueIfNeed() {
        if (resetValue)
            displayedValue = "0"
        resetValue = false
    }

    fun updateFormula() {
        var first = doubleToString(baseValue)
        var second = doubleToString(secondValue)
        var sign = getSign(lastOperation)

        if(lastOperation == Key.ROOT)
            setFormular(sign + first)
        else if(!sign.isEmpty())
            setFormular(first + sign + second)
    }

    private fun getSign(lastOperation: Key): String {

        when (lastOperation) {
            Key.DIVIDE -> return "/"
            Key.PLUS -> return "+"
            Key.MINUS -> return "-"
            Key.MULTIPLY -> return "*"
            Key.MODULO -> return "%"
            Key.POWER -> return "^"
            Key.ROOT -> return "√"
            else -> return ""
        }
    }

    fun addDigit(number : Int){
        val currentValue = displayedValue;
        val newValue = formatString(currentValue + number)
        setValue(newValue)

        val currentFormula = displayedFormula;
        val newFormula = (currentFormula + number)
        setFormular(newFormula)
    }

    private fun formatString(str:String) : String{
        if(str.contains("."))
            return str

        val doubleValue = stringToDouble(str)
        return doubleToString(doubleValue)
    }

    private fun updateResult(value : Double){
        setValue(doubleToString(value))
        baseValue = value
    }

    fun handleResult(){
        secondValue = getDisplayedNumberAsDouble()
        calculateResult()
        baseValue = getDisplayedNumberAsDouble()
    }

    private fun calculateResult() {
        if(!isFirstOperation)
            updateFormula()

        var operation = OperationFactory().forId(lastOperation, baseValue,secondValue)
        if(operation != null)
            updateResult(operation.getResult())

        isFirstOperation = false
    }

    fun getDisplayedNumberAsDouble():Double{
        return stringToDouble(displayedValue)
    }

    fun handleOperation(operation:Key){
        if(lastKey == Key.DIGIT)
            handleResult()

        resetValue = true
        lastKey = operation
        lastOperation = operation

        if(operation == Key.ROOT)
            calculateResult()
        else{
            val currentFormula = displayedFormula;
            val newFormula = (currentFormula + operation.id)
            setFormular(newFormula)
        }

    }

    fun handleClear(){
        val oldValue = displayedValue
        var newValue = "0"
        val len = oldValue.length
        var minLen = 1
        if(oldValue.contains("-"))
            minLen++

        if(len > minLen)
            newValue = oldValue.substring(0, len -1)

        newValue = newValue.replace("\\.$","")
        newValue = formatString( newValue)
        setValue(newValue)
        baseValue = stringToDouble(newValue)
    }

    fun handleReset(){
        resetValues()
        setValue("0")
        setFormular("")
    }

    fun handleEquals(){
        when(lastKey){
            Key.EQUALS->calculateResult()
        }

        if(!lastKey.equals(Key.DIGIT))
            return

        secondValue = getDisplayedNumberAsDouble()
        calculateResult()
        lastKey = Key.EQUALS

    }

    fun decimalClicked(){
        var value = displayedValue
        if(!value.contains("."))
            value+="."
        setValue(value)
    }

    fun  zeroClicked(){
        var value = displayedValue
        if(!value.equals("0"))
            addDigit(0)
    }
    fun doubleZeroClicked(){
        var value = displayedValue
        if(!value.equals("0")) {
            addDigit(0)
            addDigit(0)
        }
    }



    fun operationClicked(id : Int){
        when(id){
            R.id.btn_plus -> handleOperation(Key.PLUS)
            R.id.btn_minus -> handleOperation(Key.MINUS)
            R.id.btn_divide -> handleOperation(Key.DIVIDE)
            R.id.btn_modulo -> handleOperation(Key.MODULO)
            R.id.btn_multiply -> handleOperation(Key.MULTIPLY)
            R.id.btn_power -> handleOperation(Key.POWER)
            R.id.btn_root -> handleOperation(Key.ROOT)
        }
    }

    fun numpadClicked(id : Int){
        if(lastKey == Key.EQUALS) {
            lastOperation = Key.EQUALS
        }

        lastKey = Key.DIGIT
        resetValueIfNeed()

        when(id){
            R.id.btn_decimal -> decimalClicked()
            R.id.btn_0 -> zeroClicked()
            R.id.btn_00 -> doubleZeroClicked()
            R.id.btn_1 -> addDigit(1)
            R.id.btn_2 -> addDigit(2)
            R.id.btn_3 -> addDigit(3)
            R.id.btn_4 -> addDigit(4)
            R.id.btn_5 -> addDigit(5)
            R.id.btn_6 -> addDigit(6)
            R.id.btn_7 -> addDigit(7)
            R.id.btn_8 -> addDigit(8)
            R.id.btn_9 -> addDigit(9)
        }
    }



}