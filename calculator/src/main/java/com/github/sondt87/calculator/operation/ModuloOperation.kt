package com.github.sondt87.calculator.operation

import com.github.sondt87.calculator.operation.base.BinaryOperation
import com.github.sondt87.calculator.operation.base.Operation

class ModuloOperation(baseValue: Double, secondValue: Double) : BinaryOperation(baseValue, secondValue), Operation{

    override fun getResult():Double{
        var result = 0.0
        if(secondValue != 0.0){
            result = baseValue % secondValue
        }

        return result
    }
}