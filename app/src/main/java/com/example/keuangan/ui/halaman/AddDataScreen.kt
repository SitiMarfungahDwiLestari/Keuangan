package com.example.keuangan.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.keuangan.util.SharedViewModel
import com.example.keuangan.util.pemasukan


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
){
    var id: String by remember { mutableStateOf("") }
    var tanggal: String by remember { mutableStateOf("") }
    var nominal: String by remember { mutableStateOf("") }
    var kategori: String by remember { mutableStateOf("") }
    var nominalInt: Int by remember { mutableStateOf(0) }

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
        // add data Layout
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {

            }
            // userID
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = id,
                onValueChange = {
                    id = it
                },
                label = {
                    Text(text = "id")
                }
            )
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
                        kategori= kategori,
                        nominal = nominalInt
                    )

                    sharedViewModel.saveData(pemasukan = pemasukan, context = context)
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}