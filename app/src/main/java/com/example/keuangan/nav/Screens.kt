package com.example.keuangan.nav

sealed class Screens(val route: String) {
    object Home: Screens(route = "Home")
    object GetDataScreen: Screens(route = "get_data_screen")
    object AddDataScreen: Screens(route = "add_data_screen")
    object AddPengeluaran: Screens(route = "add_pengeluaran")
}
