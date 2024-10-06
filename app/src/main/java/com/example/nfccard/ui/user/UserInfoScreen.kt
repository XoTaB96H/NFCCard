package com.example.nfccard.ui.user

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.nfccard.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserInfoScreen(navController: NavController, viewModel: UserViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Введите ваши данные") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Имя") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text("Фамилия") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                if (name.isNotBlank() && surname.isNotBlank()) {
                    scope.launch {
                        viewModel.setUserName(name, surname)
                        navController.navigate("userPhoto") {
                            popUpTo("userInfo") { inclusive = true }
                        }
                    }
                }
            }) {
                Text("Далее")
            }
        }
    }
}
