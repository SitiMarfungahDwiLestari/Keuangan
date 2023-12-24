package com.example.keuangan.ui.halaman


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.keuangan.R
import com.example.keuangan.data.DataSource
import com.example.keuangan.data.Pemasukan
import com.example.keuangan.data.Pengeluaran
import com.example.keuangan.navigasi.DestinasiNavigasi
import com.example.keuangan.ui.theme.KeuanganTheme

enum class HomeTabs { Pemasukan, Pengeluaran }

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    navigateToItemEntry:()-> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(DestinasiHome.titleRes),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        },
    ) { innerPadding ->
        var selectedTab by rememberSaveable { mutableStateOf(HomeTabs.Pemasukan) }
        BodyHome(selectedTab = selectedTab,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize())
    }
}

@Composable
fun BodyHome(selectedTab: HomeTabs, modifier: Modifier = Modifier) {
    var selectedTab by rememberSaveable { mutableStateOf(HomeTabs.Pemasukan) }


    Column(
        modifier = modifier
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            RadioButton(
                selected = selectedTab == HomeTabs.Pemasukan,
                onClick = { selectedTab = HomeTabs.Pemasukan },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
            )
            Text(text = "Pemasukan")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = selectedTab == HomeTabs.Pengeluaran,
                onClick = { selectedTab = HomeTabs.Pengeluaran },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
            )
            Text(text = "Pengeluaran")
        }
        when (selectedTab) {
            HomeTabs.Pemasukan -> PemasukanList()
            HomeTabs.Pengeluaran -> PengeluaranList()
        }
    }
}

@Composable
fun PemasukanList() {
    LazyColumn {
        items(DataSource.pemasukanList) { pemasukan ->
            PemasukanItem(pemasukan = pemasukan)
        }
    }
}

@Composable
fun PengeluaranList() {
    LazyColumn {
        items(DataSource.pengeluaranList) { pengeluaran ->
            PengeluaranItem(pengeluaran = pengeluaran)
        }
    }
}

@Composable
fun PemasukanItem(pemasukan: Pemasukan) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle item click if needed */ }
            .padding(16.dp)
    ) {
        Text("Tanggal: ${pemasukan.tanggal}")
        Text("Nominal: ${pemasukan.nominal}")
        Text("Kategori: ${pemasukan.kategori}")
        Icon(imageVector = Icons.Default.ThumbUp, contentDescription = null)
    }
}

@Composable
fun PengeluaranItem(pengeluaran: Pengeluaran) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle item click if needed */ }
            .padding(16.dp)
    ) {
        Text("Tanggal: ${pengeluaran.tanggal}")
        Text("Nominal: ${pengeluaran.nominal}")
        Text("Deskripsi: ${pengeluaran.deskripsi}")
        Icon(imageVector = Icons.Default.Send, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    KeuanganTheme {
        Home {

        }
    }
}
