package com.ord.orderscenter.navgraph


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ord.orderscenter.screens_ui.AddOrderScreen
import com.ord.orderscenter.screens_ui.MainScreen
import com.ord.orderscenter.screens_ui.SettingScreen
import org.koin.androidx.compose.getViewModel


sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Settings : Screen("settings")

    object AddOrder : Screen("addOrder")



}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier) {


    AnimatedNavHost(
        navController,
        startDestination = Screen.Main.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
    ) {
        composable(Screen.Main.route) { MainScreen(navController) }
        composable(Screen.Settings.route) { SettingScreen(navController) }
        composable(Screen.AddOrder.route) { AddOrderScreen(navController) }



    }
}