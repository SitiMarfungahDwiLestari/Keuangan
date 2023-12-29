package com.example.keuangan.repositori

import com.example.keuangan.data.Pemasukan
import kotlinx.coroutines.flow.Flow


interface RepositoriPemasukan {
    fun getAllPemasukanStream(): Flow<List<Pemasukan>>

    fun getPemasukanStream(id: Int): Flow<Pemasukan?>

    suspend fun insertPemasukan(pemasukan: Pemasukan)

    suspend fun deletePemasukan(pemasukan: Pemasukan)

    suspend fun updatePemasukan(pemasukan: Pemasukan)
}