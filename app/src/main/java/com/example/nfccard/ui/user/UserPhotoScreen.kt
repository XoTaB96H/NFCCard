package com.example.nfccard.ui.user


import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.nfccard.viewmodel.NFCViewModel
import com.example.nfccard.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image


@Composable
fun UserPhotoScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Инициализация ViewModel-ов
    val userViewModel: UserViewModel = viewModel()
    val nfcViewModel: NFCViewModel = viewModel()

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Выберите фото") },
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
                text = "Загрузите или выберите фотографию",
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(24.dp))

            imageUri?.let {
                Image(
                    painter = rememberImagePainter(data = it),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Button(onClick = { galleryLauncher.launch("image/*") }) {
                    Text("Галерея")
                }
                Spacer(modifier = Modifier.width(16.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (imageUri != null) {
                        scope.launch {
                            userViewModel.setUserPhoto(imageUri.toString())
                            // Генерация NFC-идентификатора
                            nfcViewModel.generateNfcId()
                            navController.navigate("mainScreen") {
                                popUpTo("userPhoto") { inclusive = true }
                            }
                        }
                    }
                },
                enabled = imageUri != null
            ) {
                Text("Сохранить фото")
            }
        }
    }
}
