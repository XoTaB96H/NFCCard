package com.example.nfccard.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nfccard.model.PassEventType
import com.example.nfccard.viewmodel.MainViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = MainViewModel()) {
    val user by viewModel.user.collectAsState()
    val passHistory by viewModel.passHistory.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Отображение фотографии пользователя
        user?.photoUri?.let { uri ->
            Image(
                painter = painterResource(id = /* заменить на загрузку из URI */ 0),
                contentDescription = "User Photo",
                modifier = Modifier.size(100.dp)
            )
        }
        Text(text = "${user?.name} ${user?.surname}")
        // Отображение карты-пропуска
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // Дизайн карты
        }
        // Проверка NFC
        if (!viewModel.isNfcEnabled()) {
            // Отобразить сообщение и кнопку для включения NFC
            Button(onClick = { viewModel.enableNfc() }) {
                Text("Включить NFC")
            }
        }
        // Отображение истории проходов
        LazyColumn {
            items(passHistory) { event ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = /* форматировать дату и время */ "")
                    Icon(
                        painter = painterResource(id = if (event.eventType == PassEventType.ENTRY) R.drawable.ic_entry else R.drawable.ic_exit),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
