package com.example.keuangan.ui.halaman

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.keuangan.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit
) {
    // TextField untuk pencarian
    OutlinedTextField(
        value = searchQuery,
        onValueChange = { newQuery ->
            onSearchQueryChanged(newQuery)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = { Text("Cari...") },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null
            )
        }
    )
}
