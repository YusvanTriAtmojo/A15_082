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
import com.example.uaspam.ui.view.kandang.DetailKandangScreen
import com.example.uaspam.ui.view.kandang.EntryKdgScreen
import com.example.uaspam.ui.view.kandang.HomeKandangScreen
import com.example.uaspam.ui.view.kandang.UpdateKandangView
import com.example.uaspam.ui.view.petugas.DetailPetugasScreen
import com.example.uaspam.ui.view.petugas.EntryPtgScreen
import com.example.uaspam.ui.view.petugas.HomePetugasScreen
import com.example.uaspam.ui.view.petugas.UpdatePetugasView

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
                    navController.navigate(DestinasiHomeKandang.route)
                },
                kePetugas = {
                    navController.navigate(DestinasiHomePetugas.route)
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

        composable(
            route = DestinasiHomePetugas.route
        ) {
            HomePetugasScreen(
                navigateBack = {navController.popBackStack()},
                navigateToItemEntry = {navController.navigate(DestinasiInsertPetugas.route)},
                onDetailClick = { id ->
                    navController.navigate(("${DestinasiDetailPetugas.route}/$id"))
                }
            )
        }
        composable(DestinasiInsertPetugas.route){
            EntryPtgScreen(navigateBack = {
                navController.navigate(DestinasiHomePetugas.route){
                    popUpTo(DestinasiHomePetugas.route){
                        inclusive = true
                    }
                }
            })
        }

        composable(
            DestinasiDetailPetugas.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailPetugas.IDP){
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiDetailPetugas.IDP)
            id?.let{
                DetailPetugasScreen(
                    navigateBack = {
                        navController.navigate(DestinasiHomePetugas.route){
                            popUpTo(DestinasiHomePetugas.route){
                                inclusive = true
                            }
                        }
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdatePetugas.route}/$id")
                    },
                    onDeleteClick = {
                        navController.navigate(DestinasiHomePetugas.route) {
                            popUpTo(DestinasiHomePetugas.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        composable(
            DestinasiUpdatePetugas.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdatePetugas.IDP){
                    type = NavType.StringType
                }
            )
        ) {
            UpdatePetugasView(
                navigateBack = {navController.popBackStack()},
            )
        }
        composable(
            route = DestinasiHomeKandang.route
        ) {
            HomeKandangScreen(
                navigateBack = {navController.popBackStack()},
                navigateToItemEntry = {navController.navigate(DestinasiInsertKandang.route)},
                onDetailClick = { id ->
                    navController.navigate(("${DestinasiDetailKandang.route}/$id"))
                }
            )
        }

        composable(
            DestinasiDetailKandang.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailKandang.IDK){
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiDetailKandang.IDK)
            id?.let {
                DetailKandangScreen(
                    navigateBack = {
                        navController.navigate(DestinasiHomeKandang.route) {
                            popUpTo(DestinasiHomeKandang.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateKandang.route}/$id")
                    },
                    onDeleteClick = {
                        navController.navigate(DestinasiHomeKandang.route) {
                            popUpTo(DestinasiHomeKandang.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

        composable(DestinasiInsertKandang.route){
            EntryKdgScreen(navigateBack = {
                navController.navigate(DestinasiHomeKandang.route){
                    popUpTo(DestinasiHomeKandang.route){
                        inclusive = true
                    }
                }
            })
        }

        composable(
            DestinasiUpdateKandang.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateKandang.IDK){
                    type = NavType.StringType
                }
            )
        ) {
            UpdateKandangView(
                navigateBack = { navController.popBackStack() },
            )
        }
    }
}
