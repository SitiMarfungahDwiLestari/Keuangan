package com.example.keuangan.ui.halaman

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.keuangan.ui.theme.KeuanganTheme
import com.example.keuangan.util.PengeluaranViewModel
import com.example.keuangan.util.SharedViewModel
import com.example.keuangan.util.pemasukan
import com.example.keuangan.util.pengeluaran
import java.util.Calendar
import java.util.Date


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDataScreen(
    context: Context,
    navController: NavController,
    sharedViewModel: SharedViewModel,
    pengeluaranViewModel: PengeluaranViewModel,

){
    var id: String by remember { mutableStateOf("") }
    var tanggal: String by remember { mutableStateOf("") }
    var nominal: String by remember { mutableStateOf("") }
    var kategori: String by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var nominalInt: Int by remember { mutableStateOf(0) }
    var isPemasukanSelected by remember { mutableStateOf(true) }



    val year: Int
    val month: Int
    val day: Int

    val kalender = Calendar.getInstance()
    year = kalender.get(Calendar.YEAR)
    month = kalender.get(Calendar.MONTH)
    day = kalender.get(Calendar.DAY_OF_MONTH)
    kalender.time = Date()

    val selectedDate = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            selectedDate.value = "$dayOfMonth/${month + 1}/$year"
        }, year, month, day
    )

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
                .padding(start = 50.dp, end = 50.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isPemasukanSelected,
                    onClick = {
                        isPemasukanSelected = true
                    }
                )
                Text(text = "Pemasukan", modifier = Modifier.padding(start = 8.dp))

                // RadioButton untuk Pengeluaran
                RadioButton(
                    selected = !isPemasukanSelected,
                    onClick = {
                        isPemasukanSelected = false
                    }
                )
                Text(text = "Pengeluaran", modifier = Modifier.padding(start = 8.dp))
            }
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
            OutlinedTextField(
                value = selectedDate.value,
                onValueChange = { },
                label = { Text(text = "Tanggal") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            datePickerDialog.show()
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Date Picker")
                    }
                }
            )
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
            if (isPemasukanSelected) { OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = kategori,
                onValueChange = { kategori = it },
                label = { Text(text = "Kategori") })
            } else {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = deskripsi,
                    onValueChange = { deskripsi = it },
                    label = { Text(text = "Deskripsi") }
                )
            }

            // save Button
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    if (isPemasukanSelected) {
                        val pemasukan = pemasukan(
                            id = id,
                            tanggal = selectedDate.value,
                            kategori = kategori,
                            nominalpemasukan = nominalInt
                        )
                        sharedViewModel.saveData(pemasukan = pemasukan, context = context)
                    } else {
                        val pengeluaran = pengeluaran(
                            id = id,
                            tanggal = selectedDate.value,
                            deskripsi = deskripsi,
                            nominalpengeluaran  = nominalInt
                        )
                        pengeluaranViewModel.saveDataKeluar(pengeluaran = pengeluaran, context = context)
                    }
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}





//@Preview(showBackground = true)
//@Composable
//fun PreviewAddDataScreen() {
//    KeuanganTheme {
//        val navController = rememberNavController()
//        val sharedViewModel = SharedViewModel()
//        val pengeluaranViewModel = PengeluaranViewModel()
//        AddDataScreen(navController, sharedViewModel, pengeluaranViewModel)
//    }
//}