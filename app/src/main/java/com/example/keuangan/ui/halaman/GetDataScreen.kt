package com.example.keuangan.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keuangan.util.PengeluaranViewModel
import com.example.keuangan.util.SharedViewModel
import com.example.keuangan.data.pemasukan
import com.example.keuangan.data.pengeluaran


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    pengeluaranViewModel: PengeluaranViewModel
){
    var dataList by remember { mutableStateOf<List<pemasukan>>(emptyList()) }

    var id: String by remember { mutableStateOf("") }
    var tanggal: String by remember { mutableStateOf("") }
    var nominal: String by remember { mutableStateOf("") }
    var kategori: String by remember { mutableStateOf("") }
    var nominalInt: Int by remember { mutableStateOf(0) }
    var deskripsi : String by remember { mutableStateOf("")
    }

        val context = LocalContext.current

    // main Layout
    Column(modifier = Modifier.fillMaxSize()) {
        // back button
        Row(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
            }
        }
        // get data Layout
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // userID
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    value = id,
                    onValueChange = {
                        id = it
                    },
                    label = {
                        Text(text = "id")
                    }
                )
                // get user data Button
                Button(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(100.dp),
                    onClick = {
                        sharedViewModel.retrieveData(
                            id = id,
                            context = context
                        ) { data ->
                            tanggal = data.tanggal
                            kategori = data.kategori
                            nominal = data.nominalpemasukan.toString()
                            nominalInt = nominal.toInt()
                        }
                        pengeluaranViewModel.retrieveDataKeluar(
                            id = id,
                            context = context
                        ) { data ->
                            tanggal = data.tanggal
                            deskripsi = data.deskripsi
                            nominal = data.nominalpengeluaran.toString()
                            nominalInt = nominal.toInt()
                        }
                    }
                ) {
                    Text(text = "Get Data")
                }
            }
            // Name
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = tanggal,
                onValueChange = {
                    tanggal = it
                },
                label = {
                    Text(text = "Tanggal")
                }
            )
            // Profession
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = kategori,
                onValueChange = {
                    kategori = it
                },
                label = {
                    Text(text = "Kategori")
                }
            )
            // Profession
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = deskripsi,
                onValueChange = {
                    deskripsi = it
                },
                label = {
                    Text(text = "deskripsi")
                }
            )
            // Age
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nominal,
                onValueChange = {
                    nominal = it
                    if (nominal.isNotEmpty()) {
                        nominalInt = nominal.toInt()
                    }
                },
                label = {
                    Text(text = "Nominal")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            // save Button
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val pemasukan = pemasukan(
                        id = id,
                        tanggal = tanggal,
                        kategori = kategori,
                        nominalpemasukan = nominalInt
                    )
                    val pengeluaran = pengeluaran(
                        id = id,
                        tanggal = tanggal,
                        deskripsi = deskripsi,
                        nominalpengeluaran = nominalInt
                    )
                    pengeluaranViewModel.saveDataKeluar(pengeluaran = pengeluaran, context = context)
                    sharedViewModel.saveData(pemasukan = pemasukan, context = context)
                }
            ) {
                Text(text = "Save")
            }
            // delete Button
            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onClick = {
                    sharedViewModel.deleteData(
                        id = id,
                        context = context,
                        navController = navController
                    )

                        pengeluaranViewModel.deleteDataKeluar(
                        id = id,
                context = context,
                navController = navController
                        )}

            ) {
                Text(text = "Delete")
            }
        }
    }
}