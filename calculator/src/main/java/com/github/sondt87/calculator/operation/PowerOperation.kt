package com.github.sondt87.calculator.operation

import com.github.sondt87.calculator.operation.base.BinaryOperation
import com.github.sondt87.calculator.operation.base.Operation

class PowerOperation (baseValue: Double, secondValue:Double) : BinaryOperation(baseValue, secondValue), Operation{

    override fun getResult():Double{
        var result = Math.pow(baseValue, secondValue)
        if(result == Double.NaN || result == Double.NEGATIVE_INFINITY || result == Double.POSITIVE_INFINITY)
            result = 0.0

        return result
    }

}