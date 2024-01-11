package com.example.keuangan.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.keuangan.ui.halaman.AddDataScreen
import com.example.keuangan.ui.halaman.GetDataScreen
import com.example.keuangan.ui.halaman.Home
import com.example.keuangan.util.PengeluaranViewModel
import com.example.keuangan.util.PemasukanViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navController: NavHostController,
    pemasukanViewModel: PemasukanViewModel,
    pengeluaranViewModel: PengeluaranViewModel,

    ) {
    NavHost(
        navController = navController,
        startDestination = DestinasiNavigasi.Home.route
    ) {
        // main screen
        composable(
            route = DestinasiNavigasi.Home.route
        ) {
            Home(
                navController = navController,
            )
        }
        // get data screen
        composable(
            route = DestinasiNavigasi.GetDataScreen.route
        ) {
            GetDataScreen(
                navController = navController,
                pemasukanViewModel = pemasukanViewModel,
                pengeluaranViewModel= pengeluaranViewModel
            )
        }
        // add data screen
        composable(
            route = DestinasiNavigasi.AddDataScreen.route
        ) {
            val context = LocalContext.current
            AddDataScreen(
                context = context,
                navController = navController,
                pemasukanViewModel = pemasukanViewModel,
                pengeluaranViewModel = pengeluaranViewModel
                )
        }
    }
}