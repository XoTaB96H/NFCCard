package com.example.nfccard.ui.user

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nfccard.viewmodel.UserViewModel

@Composable
fun UserPhotoScreen(navController: NavController, viewModel: UserViewModel = UserViewModel()) {
    var imageUri by remember { mutableStateOf<String?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Выберите фотографию")
        Button(onClick = { launcher.launch("image/*") }) {
            Text("Загрузить из галереи")
        }
        imageUri?.let {
            // Отобразить выбранное изображение
            Image(
                painter = painterResource(id = /* заменить на загрузку из URI */ 0),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Button(onClick = {
                viewModel.setUserPhoto(it)
                navController.navigate("mainScreen")
            }) {
                Text("Сохранить")
            }
        }
    }
}
