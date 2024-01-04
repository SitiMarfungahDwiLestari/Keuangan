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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.keuangan.data.Kategori
import com.example.keuangan.util.pengeluaran


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
    modifier: Modifier = Modifier,

    ){
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
//        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp))
                        },
//                scrollBehavior = scrollBehavior,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)

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
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    pengeluaranViewModel: PengeluaranViewModel,
    navController: NavController
) {
    var dataList by remember { mutableStateOf<List<pemasukan>>(emptyList()) }
    var listdata by remember { mutableStateOf<List<pengeluaran>>(emptyList()) }
    var saldo by remember { mutableStateOf(0) }
    var search by remember { mutableStateOf("") }
    var searchResultDataList by remember { mutableStateOf<List<pemasukan>>(emptyList()) }
    var searchResultListData by remember { mutableStateOf<List<pengeluaran>>(emptyList()) }


    val context = LocalContext.current

    // LaunchedEffect yang dijalankan setiap kali terjadi perubahan pada dataList atau listdata
    LaunchedEffect(dataList, listdata) {
        sharedViewModel.readAllData(context) { newDataList ->
            dataList = newDataList.sortedByDescending { it.tanggal }
            searchResultDataList = newDataList.filter { it.kategori.contains(search, ignoreCase = true) }
            Log.d("GetDataScreen", "Sorted DataList: $dataList")
        }
        pengeluaranViewModel.readAllData(context) { newListData ->
            listdata = newListData.sortedByDescending { it.tanggal }
            searchResultListData = newListData.filter { it.deskripsi.contains(search, ignoreCase = true) }
            Log.d("GetDataScreen", "Sorted DataList: $listdata")
        }

        // Hitung saldo
        saldo = dataList.sumBy { it.nominalpemasukan } - listdata.sumBy { it.nominalpengeluaran }
    }

    LazyColumn(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 65.dp)
            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Saldo Terkini")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Rp $saldo")
                }
            }
        }

        item {
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
        }

        item{
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            // Tambahkan TextField untuk pencarian di sini
            Search(
                searchQuery = search,
                onSearchQueryChanged = { newQuery ->
                    search = newQuery
                    // Implementasi pencarian di sini (filter dataList dan listdata berdasarkan newQuery)
                    searchResultDataList = dataList.filter { it.kategori.contains(newQuery, ignoreCase = true) }
                    searchResultListData = listdata.filter { it.deskripsi.contains(newQuery, ignoreCase = true) }
                }
            )
        }
        // Menampilkan hasil pencarian
        items(if (search.isNotEmpty()) searchResultDataList else emptyList()) { data ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { navController.navigate(route = Screens.GetDataScreen.route) },
                shape = RoundedCornerShape(8.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Box {
                        Icon(
                            painter = painterResource(id = R.drawable.pemasukan),
                            contentDescription = "pemasukan",
                            tint = Color.Green
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))

                    Box (
                        modifier = Modifier.width(150.dp)
                    ){
                        Text(
                            text = "Rp. ${data.nominalpemasukan}",
                            style = MaterialTheme.typography.bodyLarge
                                .copy(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${data.tanggal}",
                            style = MaterialTheme.typography.bodySmall
                                .copy(fontSize = 10.sp),
                        )

                        Text(
                            text = "${data.kategori}",
                            style = MaterialTheme.typography.bodyMedium
                                .copy(fontSize = 16.sp)
                        )
                    }
                }
            }
        }
        items(if (search.isNotEmpty()) searchResultListData else emptyList()) { data ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { navController.navigate(route = Screens.GetDataScreen.route) },
                shape = RoundedCornerShape(8.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Box {
                        Icon(
                            painter = painterResource(id = R.drawable.pengeluaran),
                            contentDescription = "pengeluaran",
                            tint = Color.Red
                        )
                    }


                    Spacer(modifier = Modifier.padding(8.dp))

                    Box(
                        modifier = Modifier.width(150.dp)
                    ) {
                        Text(
                            text = "Rp. ${data.nominalpengeluaran}",
                            style = MaterialTheme.typography.bodyLarge
                                .copy(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )

                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${data.tanggal}",
                            style = MaterialTheme.typography.bodySmall
                                .copy(fontSize = 10.sp),
                        )

                        Text(
                            text = "${data.deskripsi}",
                            style = MaterialTheme.typography.bodyMedium
                                .copy(fontSize = 16.sp)
                        )
                    }
                }
            }
        }

        item {
            Text(stringResource(R.string.History),
                style = MaterialTheme.typography.bodyLarge
                .copy(
                    fontSize = 20.sp,
                ))
        }
        items(dataList) { data ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { navController.navigate(route = Screens.GetDataScreen.route) },
                shape = RoundedCornerShape(8.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Box {
                        Icon(
                            painter = painterResource(id = R.drawable.pemasukan),
                            contentDescription = "pemasukan",
                            tint = Color.Green
                        )
                    }


                    Spacer(modifier = Modifier.padding(8.dp))

                    Box (
                        modifier = Modifier.width(150.dp)
                    ){
                        Text(
                            text = "Rp. ${data.nominalpemasukan}",
                            style = MaterialTheme.typography.bodyLarge
                                .copy(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )

                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${data.tanggal}",
                            style = MaterialTheme.typography.bodySmall
                                .copy(fontSize = 10.sp),
                        )

                        Text(
                            text = "${data.kategori}",
                            style = MaterialTheme.typography.bodyMedium
                                .copy(fontSize = 16.sp)
                        )
                    }
                }
            }
        }

        items(listdata) { data ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { navController.navigate(route = Screens.GetDataScreen.route) },
                shape = RoundedCornerShape(8.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Box {
                        Icon(
                            painter = painterResource(id = R.drawable.pengeluaran),
                            contentDescription = "pengeluaran",
                            tint = Color.Red
                        )
                    }


                    Spacer(modifier = Modifier.padding(8.dp))

                    Box(
                        modifier = Modifier.width(150.dp)
                    ) {
                        Text(
                            text = "Rp. ${data.nominalpengeluaran}",
                            style = MaterialTheme.typography.bodyLarge
                                .copy(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )

                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${data.tanggal}",
                            style = MaterialTheme.typography.bodySmall
                                .copy(fontSize = 10.sp),
                        )

                        Text(
                            text = "${data.deskripsi}",
                            style = MaterialTheme.typography.bodyMedium
                                .copy(fontSize = 16.sp)
                        )
                    }
                }
            }
        }
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
