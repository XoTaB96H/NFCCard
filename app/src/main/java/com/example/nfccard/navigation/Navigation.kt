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
import com.example.nfccard.ui.user.UserInfoScreen
import com.example.nfccard.ui.splash.SplashScreen

@Composable
fun Navigation(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splashScreen") {
        composable("splashScreen") {
            SplashScreen(navController)
        }
        composable("pinSetup") {
            PINSetupScreen(navController)
        }
        composable("pinLogin") {
            PINLoginScreen(navController)
        }
        composable("userInfo") {
            UserInfoScreen(navController)
        }
        composable("userPhoto") {
            UserPhotoScreen(navController)
        }
        composable("mainScreen") {
            MainScreen(navController)
        }
    }
}
