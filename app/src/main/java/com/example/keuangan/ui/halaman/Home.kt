package com.example.keuangan.ui.halaman

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.keuangan.R
import com.example.keuangan.nav.Screens
import com.example.keuangan.ui.theme.KeuanganTheme
import com.example.keuangan.util.PengeluaranViewModel
import com.example.keuangan.util.SharedViewModel


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
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 80.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(size = 16.dp)
                )
        ) {
            // Isi kotak di sini
        }

//        Spacer(modifier = Modifier.width(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {  }) {
                Text(text = "Semua")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {  }) {
                Text(text = "Pemasukan")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {  }) {
                Text(text = "Pengeluaran")
            }
        }

        Text(stringResource(R.string.History))
    }
}








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
//}


@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    KeuanganTheme {
        val navController = rememberNavController()
        Home(navController)
    }
}