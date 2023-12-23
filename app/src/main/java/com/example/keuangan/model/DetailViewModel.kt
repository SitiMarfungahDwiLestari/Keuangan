package com.example.keuangan.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keuangan.repositori.RepositoriKeuangan
import com.example.keuangan.ui.halaman.DetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriKeuangan: RepositoriKeuangan
): ViewModel(){
    private val keuanganId: Int = checkNotNull(savedStateHandle[DetailsDestination.keuanganIdArg])

    val uiState: StateFlow<ItemDetailsUiState> =
        repositoriKeuangan.getKeuanganStream(keuanganId)
            .filterNotNull()
            .map{
                ItemDetailsUiState(detailKeuangan = it.toDetailKeuangan())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    suspend fun deleteItem(){
        repositoriKeuangan.deleteKeuangan(uiState.value.detailKeuangan.toKeuangan())
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}



data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val detailKeuangan: DetailKeuangan = DetailKeuangan()
)
