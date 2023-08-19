package com.hpy.mycalculator.ui.theme

/**
 * 计算器运算符
 * 密封类
 */
sealed class CalculatorOperation(val symbol: String) {
    object Add:CalculatorOperation("+")
    object Subtract:CalculatorOperation("-")
    object Multiply:CalculatorOperation("*")
    object Divide:CalculatorOperation("/")

    object Mod: CalculatorOperation("%")

}
