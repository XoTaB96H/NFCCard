package com.example.nfccard.ui.pin

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nfccard.viewmodel.PINViewModel


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace


@Composable
fun PINSetupScreen(navController: NavController, viewModel: PINViewModel = viewModel()) {
    val scope = rememberCoroutineScope()

    // Состояния для PIN-кода и шага установки
    var pin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var step by remember { mutableStateOf(1) }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Установка PIN-кода") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (step == 1) "Введите новый PIN-код" else "Подтвердите PIN-код",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Индикатор точек PIN-кода
            PinCodeDots(pinLength = if (step == 1) pin.length else confirmPin.length)

            Spacer(modifier = Modifier.height(24.dp))

            // Цифровая клавиатура
            NumericKeyboard(
                onNumberClick = { number ->
                    if (step == 1 && pin.length < 4) {
                        pin += number
                    } else if (step == 2 && confirmPin.length < 4) {
                        confirmPin += number
                    }

                    // Если PIN-код состоит из 4 цифр, переходим на следующий шаг или проверяем подтверждение
                    if (pin.length == 4 && step == 1) {
                        step = 2
                    } else if (confirmPin.length == 4 && step == 2) {
                        if (pin == confirmPin) {
                            scope.launch {
                                viewModel.setPinCode(pin)
                                navController.navigate("userPhoto") {
                                    popUpTo("pinSetup") { inclusive = true }
                                }
                            }
                        } else {
                            errorMessage = "PIN-коды не совпадают"
                            pin = ""
                            confirmPin = ""
                            step = 1
                        }
                    }
                },
                onDeleteClick = {
                    if (step == 1 && pin.isNotEmpty()) {
                        pin = pin.dropLast(1)
                    } else if (step == 2 && confirmPin.isNotEmpty()) {
                        confirmPin = confirmPin.dropLast(1)
                    }
                }
            )

            // Сообщение об ошибке
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
fun PinCodeDots(pinLength: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(4) { index ->
            val filled = index < pinLength
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(
                        color = if (filled) MaterialTheme.colors.primary else Color.Gray,
                        shape = CircleShape
                    )
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun NumericKeyboard(onNumberClick: (String) -> Unit, onDeleteClick: () -> Unit) {
    val keyboardRows = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("", "0", "←")
    )

    Column {
        keyboardRows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { label ->
                    when (label) {
                        "" -> Spacer(modifier = Modifier.size(64.dp))
                        "←" -> {
                            IconButton(onClick = onDeleteClick) {
                                Icon(
                                    imageVector = Icons.Default.Backspace,
                                    contentDescription = "Delete",
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                        }
                        else -> {
                            Button(
                                onClick = { onNumberClick(label) },
                                modifier = Modifier.size(64.dp)
                            ) {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.h5
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
