package com.example.nfccard.ui.pin

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nfccard.viewmodel.PINViewModel

@Composable
fun PINSetupScreen(navController: NavController, viewModel: PINViewModel = PINViewModel()) {
    var pin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var step by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = if (step == 1) "Введите новый PIN-код" else "Подтвердите PIN-код")
        // Здесь расположим индикатор точек и цифровую клавиатуру
        // ...
        // Обработка ввода PIN-кода
        // После подтверждения PIN-кода:
        if (step == 2 && pin == confirmPin) {
            viewModel.setPinCode(pin)
            navController.navigate("userPhoto")
        } else if (step == 2 && pin != confirmPin) {
            // Отобразить ошибку несовпадения PIN-кодов
        }
    }
}
