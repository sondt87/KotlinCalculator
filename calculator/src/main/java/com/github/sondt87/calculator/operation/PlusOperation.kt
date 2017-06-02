package com.github.sondt87.calculator.operation

import com.github.sondt87.calculator.operation.base.BinaryOperation
import com.github.sondt87.calculator.operation.base.Operation

class PlusOperation(baseValue: Double, secondValue: Double) : BinaryOperation(baseValue, secondValue), Operation{
    override fun getResult(): Double {
        return baseValue + secondValue;
    }
}