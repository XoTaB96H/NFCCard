package com.example.nfccard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nfccard.ui.main.MainScreen
import com.example.nfccard.ui.pin.PINLoginScreen
import com.example.nfccard.ui.pin.PINSetupScreen
import com.example.nfccard.ui.user.UserPhotoScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "pinSetup") {
        composable("pinSetup") {
            PINSetupScreen(navController)
        }
        composable("pinLogin") {
            PINLoginScreen(navController)
        }
        composable("mainScreen") {
            MainScreen(navController)
        }
        composable("userPhoto") {
            UserPhotoScreen(navController)
        }
    }
}
