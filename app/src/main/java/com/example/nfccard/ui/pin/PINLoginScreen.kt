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
fun PINLoginScreen(navController: NavController, viewModel: PINViewModel = PINViewModel()) {
    var pin by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Введите ваш PIN-код")
        // Индикатор точек и цифровая клавиатура
        // ...
        // Обработка ввода PIN-кода
        if (viewModel.verifyPinCode(pin)) {
            navController.navigate("mainScreen")
        } else {
            // Отобразить сообщение об ошибке
        }
    }
}
