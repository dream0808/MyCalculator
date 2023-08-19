package com.hpy.mycalculator.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

/**
 * 单个按钮布局
 */
@Composable
fun CalculatorButton(
    modifier: Modifier,//提供被使用处调整外观
    symbol:String,//符号：数字或加减乘除
    onClick:()->Unit//按钮监听事件，向viewModel发送事件
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
        .clip(CircleShape)//修剪（圆形）
        .clickable { onClick() }//可点击的
        .then(modifier)
    ){
       Text(text = symbol, fontSize = 36.sp, color = Color.White)
    }
}