package com.github.sondt87.calculator.operation

enum class Key(val id : String){
    EMPTY(""),

    EQUALS("="),
    DIGIT("digit"),
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    MODULO("%"),
    POWER("^"),
    ROOT("√"),
    DECIMAL("."),
    CLEAR("C"),
    RESET("reset"),

    DOUBLE_ZERO("00"),
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
}