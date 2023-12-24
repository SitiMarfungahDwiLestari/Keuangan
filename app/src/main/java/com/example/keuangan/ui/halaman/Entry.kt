package com.example.keuangan.ui.halaman


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.keuangan.R
import com.example.keuangan.navigasi.DestinasiNavigasi
import com.example.keuangan.ui.theme.KeuanganTheme


enum class EntryType{Pemasukan, Pengeluaran}

object DestinasiEntry: DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = R.string.entity_data
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Entry(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {}
)
{
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
    ){
        innerPadding ->
        BodyEntry(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun BodyEntry(modifier: Modifier = Modifier){
    var selectedOption by remember { mutableStateOf(EntryType.Pemasukan) }
    Column (
        modifier = modifier
            .padding (16.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            RadioButton(
                selected = selectedOption == EntryType.Pemasukan,
                onClick = { selectedOption = EntryType.Pemasukan},
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
            )
            Text(text = "Pemasukan")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = selectedOption == EntryType.Pengeluaran,
                onClick = { selectedOption = EntryType.Pengeluaran },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
            )
            Text(text = "Pengeluaran")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEntry() {
    KeuanganTheme {
        Entry()
    }
}