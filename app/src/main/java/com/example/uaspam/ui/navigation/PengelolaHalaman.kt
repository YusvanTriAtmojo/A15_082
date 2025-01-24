package com.example.uaspam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uaspam.ui.view.HomeView
import com.example.uaspam.ui.view.hewan.DetailHewanScreen
import com.example.uaspam.ui.view.hewan.EntryHwnScreen
import com.example.uaspam.ui.view.hewan.HomeHewanScreen
import com.example.uaspam.ui.view.hewan.UpdateHewanView

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(route = "home") {
            HomeView(
                keHewan = {
                    navController.navigate(DestinasiHomeHewan.route)
                },
                keKandang = {

                },
                kePetugas = {

                },
                keMonitoring = {

                },
            )
        }
        composable(
            route = DestinasiHomeHewan.route
        ) {
            HomeHewanScreen(
                navigateBack = {navController.popBackStack()},
                navigateToItemEntry = {navController.navigate(DestinasiInsertHewan.route)},
                onDetailClick = { id ->
                    navController.navigate(("${DestinasiDetailHewan.route}/$id"))
                }
            )
        }
        composable(DestinasiInsertHewan.route){
            EntryHwnScreen(navigateBack = {
                navController.navigate(DestinasiHomeHewan.route){
                    popUpTo(DestinasiHomeHewan.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailHewan.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailHewan.IDH){
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiDetailHewan.IDH)
            id?.let{
                DetailHewanScreen(
                    navigateBack = {
                        navController.navigate(DestinasiHomeHewan.route){
                            popUpTo(DestinasiHomeHewan.route){
                                inclusive = true
                            }
                        }
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateHewan.route}/$id")
                    },
                    onDeleteClick = {
                        navController.navigate(DestinasiHomeHewan.route) {
                            popUpTo(DestinasiHomeHewan.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        composable(
            DestinasiUpdateHewan.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateHewan.IDH){
                    type = NavType.StringType
                }
            )
        ) {
            UpdateHewanView(
                navigateBack = {navController.popBackStack()},
                navigate = {navController.popBackStack()}
            )
        }
    }
}