package com.example.keuangan.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keuangan.R
import com.example.keuangan.nav.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
){
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = Screens.AddDataScreen.route)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.entity_data)
                )
            }
        },
    ) { innerPadding ->
        BodyHome(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
           )
    }
}


















@Composable
fun BodyHome(
    modifier: Modifier,
    ) {
//    Column(
//        modifier = Modifier
//            .padding(start = 50.dp, end = 50.dp)
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // get user data Button
//        Button(
//            modifier = Modifier.fillMaxWidth(),
//            onClick = {
//                navController.navigate(route = Screens.GetDataScreen.route)
//            }
//        ) {
//            Text(text = "Get All Data")
//        }
//
//        // add user data Button
//        OutlinedButton(
//            modifier = Modifier.fillMaxWidth(),
//            onClick = {
//                navController.navigate(route = Screens.AddDataScreen.route)
//            }
//        ) {
//            Text(text = "Add Pemasukan")
//        }
//
//        OutlinedButton(
//            modifier = Modifier.fillMaxWidth(),
//            onClick = {
//                navController.navigate(route = Screens.AddPengeluaran.route)
//            }
//        ) {
//            Text(text = "Add Pengeluaran")
//        }
//    }
}