package com.example.keuangan.ui.halaman

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import com.example.keuangan.util.pemasukan
import androidx.compose.ui.platform.LocalContext
import com.example.keuangan.util.pengeluaran


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
    modifier: Modifier = Modifier,
    ){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

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
            sharedViewModel = SharedViewModel(),
            pengeluaranViewModel = PengeluaranViewModel(),
            navController = navController
           )
    }
}

@Composable
fun BodyHome(
    modifier: Modifier,
    sharedViewModel: SharedViewModel,
    pengeluaranViewModel: PengeluaranViewModel,
    navController: NavController
) {
    var dataList by remember { mutableStateOf<List<pemasukan>>(emptyList()) }
    var listdata by remember { mutableStateOf<List<pengeluaran>>(emptyList()) }

    val context = LocalContext.current


    DisposableEffect(Unit) {
        sharedViewModel.readAllData(context) { newDataList ->
            dataList = newDataList
            Log.d("GetDataScreen", "DataList size: ${dataList.size}")
        }
        pengeluaranViewModel.readAllData(context) { newListData ->
            listdata = newListData
            Log.d("GetDataScreen", "DataList size: ${listdata.size}")
        }
        onDispose { }
    }

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
            Button(onClick = {
                sharedViewModel.readAllData(context) { newDataList ->
                    dataList = newDataList
                    Log.d("GetDataScreen", "DataList size: ${dataList.size}")
                }
                pengeluaranViewModel.readAllData(context) { newListData ->
                    listdata = newListData
                    Log.d("GetDataScreen", "DataList size: ${listdata.size}")
                }

            }) {
                Text(text = "Semua")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                sharedViewModel.readAllData(context) { newDataList ->
                    dataList = newDataList
                    Log.d("GetDataScreen", "DataList size: ${dataList.size}")
                }
            }) {
                Text(text = "Pemasukan")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                pengeluaranViewModel.readAllData(context) { newListData ->
                    listdata = newListData
                    Log.d("GetDataScreen", "DataList size: ${listdata.size}")
                }
            }) {
                Text(text = "Pengeluaran")
            }
        }
        Text(stringResource(R.string.History))
        LazyColumn {
            items(dataList) { data ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navController.navigate(route = Screens.GetDataScreen.route) },
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "ID: ${data.id}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Tanggal: ${data.tanggal}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Nominal: ${data.nominal}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Kategori: ${data.kategori}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            items(listdata) { data ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navController.navigate(route = Screens.GetDataScreen.route)  },
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "ID: ${data.id}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Tanggal: ${data.tanggal}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Nominal: ${data.nominal}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "deskripsi: ${data.deskripsi}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }







//
//
//        LazyColumn {
//            items(dataList) { data ->
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                        .clickable { navController.navigate(route = Screens.GetDataScreen.route) },
//                    shape = RoundedCornerShape(8.dp),
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp)
//                    ) {
//                        Text(
//                            text = "ID: ${data.id}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = "Tanggal: ${data.tanggal}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = "Nominal: ${data.nominal}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = "Kategori: ${data.kategori}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                    }
//                }
//            }
//            items(listdata) { data ->
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                        .clickable { navController.navigate(route = Screens.GetDataScreen.route) },
//                    shape = RoundedCornerShape(8.dp),
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp)
//                    ) {
//                        Text(
//                            text = "ID: ${data.id}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = "Tanggal: ${data.tanggal}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = "Nominal: ${data.nominal}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = "deskripsi: ${data.deskripsi}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                    }
//                }
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    KeuanganTheme {
        val navController = rememberNavController()
        Home(navController)
    }
}