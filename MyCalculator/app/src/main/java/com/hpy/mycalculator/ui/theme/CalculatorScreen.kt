package com.hpy.mycalculator.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


val buttonSymbol = arrayListOf(
    arrayOf("Ac" to AcColor, "( )" to SymbolColor, "%" to SymbolColor, "/" to SymbolColor),
    arrayOf(
        "7" to BackGroundColor,
        "8" to BackGroundColor,
        "9" to BackGroundColor,
        "*" to SymbolColor
    ),
    arrayOf(
        "4" to BackGroundColor,
        "5" to BackGroundColor,
        "6" to BackGroundColor,
        "-" to SymbolColor
    ),
    arrayOf(
        "1" to BackGroundColor,
        "2" to BackGroundColor,
        "3" to BackGroundColor,
        "+" to SymbolColor
    ),
    arrayOf(
        "0" to BackGroundColor,
        "." to BackGroundColor,
        "De" to BackGroundColor,
        "=" to EqualColor
    ),
)

@Composable
fun CalculatorScreen(
    modifier: Modifier, state: CalculatorState, onEvents: (CalculatorEvents) -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {


            Text(
                modifier = Modifier
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth(),
                text = state.leftNumber + (state.operation?.symbol ?: "") + state.rightNumber,
                textAlign = TextAlign.End,//文本对齐
                fontSize = 70.sp,
                lineHeight = 70.sp,//行间距
                softWrap = true,//内容是否自动换行
                maxLines = 3,
                color = Color.White
            )
            buttonSymbol.forEach {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    it.forEach {
                        CalculatorButton(modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(it.second),
                            symbol = it.first,
                            onClick = {
                                when (it.first) {
                                    in "0" -> onEvents(CalculatorEvents.Number(0))
                                    in "1" -> onEvents(CalculatorEvents.Number(1))
                                    in "2" -> onEvents(CalculatorEvents.Number(2))
                                    in "3" -> onEvents(CalculatorEvents.Number(3))
                                    in "4" -> onEvents(CalculatorEvents.Number(4))
                                    in "5" -> onEvents(CalculatorEvents.Number(5))
                                    in "6" -> onEvents(CalculatorEvents.Number(6))
                                    in "7" -> onEvents(CalculatorEvents.Number(7))
                                    in "8" -> onEvents(CalculatorEvents.Number(8))
                                    in "9" -> onEvents(CalculatorEvents.Number(9))
                                    in "Ac" -> onEvents(CalculatorEvents.Clear)
                                    in "+" -> onEvents(
                                        CalculatorEvents.Operation(
                                            CalculatorOperation.Add
                                        )
                                    )

                                    in "-" -> onEvents(
                                        CalculatorEvents.Operation(
                                            CalculatorOperation.Subtract
                                        )
                                    )

                                    in "*" -> onEvents(
                                        CalculatorEvents.Operation(
                                            CalculatorOperation.Multiply
                                        )
                                    )

                                    in "/" -> onEvents(
                                        CalculatorEvents.Operation(
                                            CalculatorOperation.Divide
                                        )
                                    )

                                    in "." -> onEvents(CalculatorEvents.Decimal)
                                    in "=" -> onEvents(CalculatorEvents.Calculator)
                                    in "De" -> onEvents(CalculatorEvents.Delete)
                                    in "%" -> onEvents(
                                        CalculatorEvents.Operation(
                                            CalculatorOperation.Mod
                                        )
                                    )

                                    else -> {

                                    }
                                }
                            })

                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShowHome() {
    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state

    CalculatorScreen(
        state = state,
        onEvents = viewModel::onEvents,
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .padding(16.dp)
    )

}