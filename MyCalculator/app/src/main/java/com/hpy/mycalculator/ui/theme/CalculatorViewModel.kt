package com.hpy.mycalculator.ui.theme

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CalculatorViewModel : ViewModel() {
    //初始化语句，initializer（初始化），getter和setter都是可选的
    var state by mutableStateOf(CalculatorState())

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }

    fun onEvents(events: CalculatorEvents) {
        when (events) {
            //is 检测一个值具有指定类型
            is CalculatorEvents.Number -> enterNumber(events.number)//输入数字
            is CalculatorEvents.Operation -> enterOperation(events.operation)//输入运算符
            is CalculatorEvents.Delete -> performDeletion()//执行删除
            is CalculatorEvents.Decimal -> enterDecimal()//输入小数点
            is CalculatorEvents.Calculator -> performCalculation()//计算结果
            is CalculatorEvents.Clear -> state = CalculatorState()//清屏，回到原始状态

        }
    }

    /**
     * 计算结果
     */
    private fun performCalculation() {
        val number1 = state.leftNumber.toDoubleOrNull()
        val number2 = state.rightNumber.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = when (state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                is CalculatorOperation.Mod -> number1 % number2
                null -> return
            }
            //去除末尾的小数点
           val result2 = "$result".dropLastWhile { it == '0' }.dropLastWhile { it == '.' }


            state = state.copy(
                //take 返回包含此字符串中前n个字的串
                leftNumber = result2.take(15),
                rightNumber = "",
                operation = null
            )
        }
    }

    /**
     * 输入小数点
     */
    private fun enterDecimal() {
        //如果运算符为空 并且 不包含小数点 并且数字不为空
        if (state.operation == null
            && !state.leftNumber.contains(".")
            && state.leftNumber.isNotBlank()
        ) {
            state = state.copy(leftNumber = state.leftNumber + ".")
            return
        }
        //判断rightNumber是否包含"."，并且是否为空
        if (!state.rightNumber.contains(".")
            && state.rightNumber.isNotBlank()
        ) {
            state = state.copy(rightNumber = state.rightNumber + ".")
            return
        }
    }

    /**
     * 删除输入
     *
     */
    private fun performDeletion() {
        when {
            //当rightNumber不是空的时候，执行删除一位数
            state.rightNumber.isNotBlank() -> state = state.copy(
                //dropLast 返回删除了最后n个字节的字串
                rightNumber = state.rightNumber.dropLast(1)
            )
            //当运算符不是空的时候，执行删除运算符
            state.operation != null -> state = state.copy(
                operation = null
            )
            //当leftNumber不为空时，执行删除一位数
            state.leftNumber.isNotBlank() -> state = state.copy(
                leftNumber = state.leftNumber.dropLast(1)
            )
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        //当leftNumber不为空时输入运算符
        if (state.leftNumber.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    /**
     * 输入数字
     * 长度限定为8位数（千万）
     */
    private fun enterNumber(number: Int) {
        //当加减乘除符号为空时才执行
        if (state.operation == null) {
            //如果长度超过8位，停止输入
            if (state.leftNumber.length >= MAX_NUM_LENGTH) {
                return
            }
            //使用copy
            state = state.copy(leftNumber = state.leftNumber + number)
            return
        }
        if (state.rightNumber.length >= MAX_NUM_LENGTH) {
            return
        }
        //使用copy
        state = state.copy(rightNumber = state.rightNumber + number)

    }
}