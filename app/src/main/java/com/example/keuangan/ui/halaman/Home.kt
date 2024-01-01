package com.example.keuangan.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keuangan.nav.Screens


@Composable
fun Home(
    navController: NavController,
){
    Column(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // get user data Button
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = Screens.GetDataScreen.route)
            }
        ) {
            Text(text = "Get All Data")
        }

        // add user data Button
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = Screens.AddDataScreen.route)
            }
        ) {
            Text(text = "Add Pemasukan")
        }

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = Screens.AddPengeluaran.route)
            }
        ) {
            Text(text = "Add Pengeluaran")
        }
    }
}