package com.github.sondt87.calculator.operation

import com.github.sondt87.calculator.operation.base.Operation
import com.github.sondt87.calculator.operation.base.UnaryOperation

class RootOperation(value: Double) : UnaryOperation(value), Operation{

    override fun getResult(): Double {
        return Math.sqrt(value)
    }
}