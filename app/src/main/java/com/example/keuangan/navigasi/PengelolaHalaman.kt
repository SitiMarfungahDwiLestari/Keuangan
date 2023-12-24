package com.example.keuangan.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.keuangan.ui.halaman.DetailsDestination
import com.example.keuangan.ui.halaman.Entry
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
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
    ){
         //Destinasi Home
        composable("home"){
            Home(navController = navController)
        }

        //Destinasi Entry
        composable("entry"){
            Entry(navController = navController)
        }
    }
}