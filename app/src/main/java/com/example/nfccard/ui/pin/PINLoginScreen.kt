package com.example.nfccard.ui.pin

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nfccard.viewmodel.PINViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PINLoginScreen(navController: NavController, viewModel: PINViewModel = viewModel()) {
    val scope = rememberCoroutineScope()

    // Состояние для PIN-кода и сообщения об ошибке
    var pin by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Вход в систему") },
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
                text = "Введите PIN-код",
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Индикатор точек PIN-кода
            PinCodeDots(pinLength = pin.length)

            Spacer(modifier = Modifier.height(24.dp))

            // Цифровая клавиатура
            NumericKeyboard(
                onNumberClick = { number ->
                    if (pin.length < 4) {
                        pin += number
                    }
                    if (pin.length == 4) {
                        scope.launch {
                            if (viewModel.verifyPinCode(pin)) {
                                navController.navigate("mainScreen") {
                                    popUpTo("pinLogin") { inclusive = true }
                                }
                            } else {
                                errorMessage = "Неверный PIN-код"
                                pin = ""
                            }
                        }
                    }
                },
                onDeleteClick = {
                    if (pin.isNotEmpty()) {
                        pin = pin.dropLast(1)
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
private fun PinCodeDots(pinLength: Int) {
    Row(horizontalArrangement = Arrangement.Center) {
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
