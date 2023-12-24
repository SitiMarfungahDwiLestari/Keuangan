package com.example.keuangan.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.keuangan.ui.halaman.DestinasiHome
import com.example.keuangan.ui.halaman.Home

//Inisiasi Navigasi
@Composable
fun AplikasiKeuangan(navController: NavHostController = rememberNavController()){
    HostNavigasi(navController = navController)
}

//back di top bar
@Composable
fun KeuanganTopBar(){

}


@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
    ){
        // Destinasi Home
        //composable(DestinasiHome.route){
           // Home(
                //navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                //onDetailClick = {
//                    navController.navigate("${DetailsDestination.route}/$it")
//                }
//                )
//        }

        //Destinasi Entry
//        composable(DestinasiEntry.route){
//            Entry(navigateBack = {navController.popBackStack()})
//        }
    }
}