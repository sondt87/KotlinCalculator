package com.github.sondt87.calculator.operation

import com.github.sondt87.calculator.operation.base.Operation

class OperationFactory {
    fun forId(id : Key, baseValue: Double, secondValue:Double) : Operation? {
        when(id) {
            Key.MINUS -> return MinusOperation(baseValue, secondValue)
            Key.PLUS -> return PlusOperation(baseValue, secondValue)
            Key.DIVIDE -> return DivideOperation(baseValue, secondValue)
            Key.MULTIPLY -> return MultiplyOperation(baseValue, secondValue)
            Key.MODULO -> return ModuloOperation(baseValue, secondValue)
            Key.POWER-> return PowerOperation(baseValue, secondValue)
            Key.ROOT-> return RootOperation(baseValue)
            else -> return null
        }
    }
}