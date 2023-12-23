package com.example.keuangan.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keuangan.repositori.RepositoriKeuangan
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriKeuangan: RepositoriKeuangan
): ViewModel(){
    var keuanganUiState by mutableStateOf(UIStateKeuangan())
        private set
    private val itemId: Int = 0

    init {
        viewModelScope.launch {
            keuanganUiState = repositoriKeuangan.getKeuanganStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateKeuangan(true)
        }
    }

    suspend fun updateKeuangan(){
        if(validasiInput(keuanganUiState.detailKeuangan)){
            repositoriKeuangan.updateKeuangan(keuanganUiState.detailKeuangan.toKeuangan())
        }
        else{
            println("Data Tidak Valid")
        }
    }

    fun updateUiState(detailKeuangan: DetailKeuangan){
        keuanganUiState =
            UIStateKeuangan(detailKeuangan = detailKeuangan, isEntryValid = validasiInput(detailKeuangan))
    }

    private fun validasiInput(uiState: DetailKeuangan = keuanganUiState.detailKeuangan) : Boolean{
        return with(uiState){
            tipe.isNotBlank() && kategori.isNotBlank()
        }
    }
}