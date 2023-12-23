package com.example.keuangan.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.keuangan.AplikasiKeuangan


object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiKeuangan().container.repositoriKeuangan)
        }

        initializer {
            EntryViewModel(aplikasiKeuangan().container.repositoriKeuangan)
        }

        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiKeuangan().container.repositoriKeuangan
            )
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiKeuangan().container.repositoriKeuangan
            )
        }
    }
}

fun CreationExtras.aplikasiKeuangan(): AplikasiKeuangan = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiKeuangan)


