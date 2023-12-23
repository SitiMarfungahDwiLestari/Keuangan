package com.example.keuangan.model

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.keuangan.data.Keuangan
import com.example.keuangan.repositori.RepositoriKeuangan

class EntryViewModel(private val repositoriKeuangan: RepositoriKeuangan) : ViewModel() {
    var uiStateKeuangan by mutableStateOf(UIStateKeuangan())
        private set

    fun setDetailKeuangan(detailKeuangan: DetailKeuangan) {
        uiStateKeuangan = uiStateKeuangan.copy(detailKeuangan = detailKeuangan)
    }

    fun setIsEntryValid(isValid: Boolean) {
        uiStateKeuangan = uiStateKeuangan.copy(isEntryValid = isValid)
    }
}

data class UIStateKeuangan(
    val detailKeuangan: DetailKeuangan = DetailKeuangan(),
    val isEntryValid: Boolean = false
)

data class DetailKeuangan(
    val dateAdded: Long = System.currentTimeMillis(),
    val kategori: String = "",
    val tipe : String = "",
    val nominal: Int = 0,
    val id: Int = 0,
    val deskripsi: String = " "
)

fun DetailKeuangan.toKeuangan(): Keuangan = Keuangan(
    dateAdded = dateAdded,
    kategori = kategori,
    nominal = nominal,
    deskripsi = deskripsi,
    id = id
)

fun Keuangan.toUiStateKeuangan(isEntryValid: Boolean = false): UIStateKeuangan = UIStateKeuangan(
    detailKeuangan = this.toDetailKeuangan(),
    isEntryValid = isEntryValid
)

fun Keuangan.toDetailKeuangan(): DetailKeuangan = DetailKeuangan(
    dateAdded = dateAdded,
    kategori = kategori,
    nominal = nominal,
    deskripsi = deskripsi,
    id = id
)
