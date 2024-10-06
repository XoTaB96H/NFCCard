package com.example.nfccard.ui.splash

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.example.nfccard.viewmodel.SplashViewModel

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = viewModel()) {
    val isPinSet by viewModel.isPinSet.collectAsState(initial = null)

    LaunchedEffect(isPinSet) {
        if (isPinSet != null) {
            if (isPinSet == true) {
                navController.navigate("pinLogin") {
                    popUpTo("splashScreen") { inclusive = true }
                }
            } else {
                navController.navigate("pinSetup") {
                    popUpTo("splashScreen") { inclusive = true }
                }
            }
        }
    }

    // Здесь можно добавить логотип или анимацию загрузки
}
