package com.example.carparkingsystem.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carparkingsystem.data.AuthViewModel
import com.example.carparkingsystem.ui.theme.screens.cars.AddCarScreen
import com.example.carparkingsystem.ui.theme.screens.cars.CarListScreen
import com.example.carparkingsystem.ui.theme.screens.dashboard.Dashboard
import com.example.carparkingsystem.ui.theme.screens.login.LoginScreen
import com.example.carparkingsystem.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(),
               startDestination: String = ROUTE_REGISTER)
{
    val authViewModel : AuthViewModel = viewModel()


    NavHost(navController = navController, startDestination = startDestination) {
        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable(ROUTE_CARS) { AddCarScreen(navController) }
        composable(ROUTE_CAR) { CarListScreen(navController,cars = listOf()) }
        composable(ROUTE_DASHBOARD) {
            Dashboard(
                navController,
                onLogoutClick = {
                    authViewModel.logout(navController, ROUTE_LOGIN)
                })
        }
    }
}