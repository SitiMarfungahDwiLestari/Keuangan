package com.example.keuangan.repositori

import com.example.keuangan.data.Pemasukan
import com.example.keuangan.data.Pengluaran
import kotlinx.coroutines.flow.Flow


interface RepositoriPengluaran {
    fun getAllPengluaranStream(): Flow<List<Pengluaran>>

    fun getPengluaranStream(id: Int): Flow<Pengluaran?>

    suspend fun insertPengluaran(pengluaran: Pengluaran)

    suspend fun deletePengluaran(pengluaran: Pengluaran)

    suspend fun updatePengluaran(pengluaran: Pengluaran)
}