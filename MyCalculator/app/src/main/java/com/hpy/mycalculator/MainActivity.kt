package com.hpy.mycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hpy.mycalculator.ui.theme.BackGroundColor
import com.hpy.mycalculator.ui.theme.CalculatorScreen
import com.hpy.mycalculator.ui.theme.CalculatorViewModel
import com.hpy.mycalculator.ui.theme.MyCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCalculatorTheme {
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
        }
    }
}

