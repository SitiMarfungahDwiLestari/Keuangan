package com.example.keuangan.ui.halaman

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.keuangan.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDataForm(onNavigateBack: () -> Unit = {}) {
    var selectedTransactionType by remember { mutableStateOf<TransactionType?>(null) }

    val transactionTypes = listOf(TransactionType.Pemasukan, TransactionType.Pengeluaran)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.add_data_title)) },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Text(
                        text = stringResource(R.string.select_transaction_type),
                    )

                    // Radio Buttons for Transaction Type
                    transactionTypes.forEach { type ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (selectedTransactionType == type),
                                    onClick = {
                                        selectedTransactionType = type
                                    }
                                )
                                .padding(vertical = 8.dp)
                        ) {
                            RadioButton(
                                selected = (selectedTransactionType == type),
                                onClick = {
                                    selectedTransactionType = type
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = colorResource(id = R.color.purple_500),
                                    unselectedColor = Color.Gray
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = type.name)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.select_category)
                    )
                }
            }
        }
    )
}

enum class TransactionType {
    Pemasukan,
    Pengeluaran
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewAddDataForm() {
    //CobaKeuanganTheme {
        //AddDataForm()
    //}
//}
