package com.hpy.mycalculator.ui.theme

sealed class CalculatorEvents {
    data class Number(val number:Int) : CalculatorEvents()

    object Clear :CalculatorEvents()//清除
    object Delete :CalculatorEvents()//删除
    object Decimal :CalculatorEvents()//小数点
    object Calculator :CalculatorEvents()//执行计算

    data class Operation(val operation:CalculatorOperation) : CalculatorEvents()
}