package com.example.byawatjetpcompest.Nagavation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost

import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.byawatjetpcompest.Moudle.ByawModleView
import com.example.byawatjetpcompest.Screens.HomeScreenMapa

@Composable
fun Nagvation(navController: NavHostController,modifier: Modifier=Modifier,viewModel : ByawModleView) {


    NavHost(navController = navController, startDestination = "MainPage") {
        composable("MainPage") {
            HomeScreenMapa(modifier,viewModel=viewModel)
        }


    }
}