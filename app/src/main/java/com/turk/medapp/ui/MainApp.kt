package com.turk.medapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.turk.medapp.core.utils.MedicineNavType
import com.turk.medapp.core.utils.Screen
import com.turk.medapp.data.dtos.Medicine
import com.turk.medapp.ui.main.MainViewModel
import com.turk.medapp.ui.screens.LoginScreen
import com.turk.medapp.ui.screens.MedicineCardDetail
import com.turk.medapp.ui.screens.MedicineListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainApp(appState: AppState) {


    val mainViewModel = hiltViewModel<MainViewModel>()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = appState.snackbarHostState) },
            containerColor = Color.Transparent,
            contentColor = Color.White
        ) {
            NavHost(
                navController = appState.navController,
                startDestination = Screen.LoginScreen.route
            ) {

                composable(route = Screen.LoginScreen.route) {
                    LoginScreen(mainViewModel,appState) {

                        appState.navController.navigate(Screen.MedicineListScreen.route)

                    }
                }

                composable(route = Screen.MedicineListScreen.route) {
                    MedicineListScreen(mainViewModel,{
                        appState.navController.navigate(Screen.MedicineDetailedScreen.createRoute(it))
                    },{
                        appState.navController.popBackStack()
                    })
                }
                composable(
                    route = Screen.MedicineDetailedScreen.route,
                    arguments = listOf(navArgument("data") { type = MedicineNavType() })
                ) { backStackEntry ->
                    val medicine = backStackEntry.arguments?.getParcelable<Medicine>("data")
                    MedicineCardDetail(medicine){
                        appState.navController.popBackStack()
                    }
                }

            }

        }

    }
}