package com.example.keuangan.nav

sealed class DestinasiNavigasi(val route: String) {
    object Home: DestinasiNavigasi(route = "Home")
    object GetDataScreen: DestinasiNavigasi(route = "get_data_screen")
    object AddDataScreen: DestinasiNavigasi(route = "add_data_screen")
}
