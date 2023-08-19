package com.hpy.mycalculator.ui.theme

/**
 * 状态
 */

data class CalculatorState(
    val leftNumber: String = "",//第一次输入的状态（例如：加数）
    val rightNumber: String = "",//第二次输入的状态（例如：被加数）
    val operation: CalculatorOperation? = null//运算符（+-*/）的状态（例如：+）
)