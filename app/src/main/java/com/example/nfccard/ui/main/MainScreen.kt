package com.example.nfccard.ui.main

//noinspection UsingMaterialAndMaterial3Libraries


import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.nfccard.model.PassEvent
import com.example.nfccard.model.PassEventType
import com.example.nfccard.viewmodel.MainViewModel


@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
    val user by viewModel.user.collectAsState()
    val passHistory by viewModel.passHistory.collectAsState(initial = emptyList())
    var isNfcEnabled by remember { mutableStateOf(viewModel.isNfcEnabled()) }

    LaunchedEffect(Unit) {
        isNfcEnabled = viewModel.isNfcEnabled()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Главная") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Фотография пользователя
            user?.photoUri?.let { uri ->
                Image(
                    painter = rememberImagePainter(data = uri),
                    contentDescription = "User Photo",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Имя и фамилия
            Text(
                text = "${user?.name ?: ""} ${user?.surname ?: ""}",
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Карта-пропуск
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                elevation = 4.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.primaryVariant)
                            )
                        )
                ) {
                    Text(
                        text = "Ваш пропуск",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Проверка NFC
            if (!isNfcEnabled) {
                Text(text = "NFC отключен", color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    navController.context.startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
                }) {
                    Text("Включить NFC")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // История проходов
            Text(text = "История проходов", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))

            if (passHistory.isNotEmpty()) {
                LazyColumn {
                    items(passHistory) { event ->
                        PassEventItem(event)
                    }
                }
            } else {
                Text(text = "История пуста")
            }
        }
    }
}

@Composable
fun PassEventItem(event: PassEvent) {
    val dateTime = java.text.SimpleDateFormat("dd.MM.yyyy HH:mm").format(event.timestamp)
    val eventIcon = if (event.eventType == PassEventType.ENTRY) {
        Icons.Default.ArrowUpward
    } else {
        Icons.Default.ArrowDownward
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = dateTime)
        Icon(imageVector = eventIcon, contentDescription = null)
    }
}

