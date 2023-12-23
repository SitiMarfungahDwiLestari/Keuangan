package com.example.keuangan.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keuangan.data.Keuangan
import com.example.keuangan.repositori.RepositoriKeuangan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class HomeViewModel (private val repositoriKeuangan: RepositoriKeuangan): ViewModel(){
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoriKeuangan.getAllKeuanganStream()
        .filterNotNull()
        .map { HomeUiState(listKeuangan = it.toList()) }
        .stateIn(scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState())

    data class HomeUiState(
        val listKeuangan: List<Keuangan> = listOf()
    )
}