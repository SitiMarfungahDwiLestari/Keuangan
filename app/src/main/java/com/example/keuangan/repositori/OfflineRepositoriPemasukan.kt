package com.example.keuangan.repositori

import com.example.keuangan.data.Pemasukan
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriPemasukan(private val dao: com.example.keuangan.data.PemasukanDao): RepositoriPemasukan {
    override fun getAllPemasukanStream(): Flow<List<Pemasukan>> = dao.getAllPemasukan()

    override fun getPemasukanStream(id: Int): Flow<Pemasukan?> = dao.getPemasukan(id)

    override suspend fun insertPemasukan(pemasukan: Pemasukan) = dao.insert(pemasukan)

    override suspend fun deletePemasukan(pemasukan: Pemasukan) = dao.delete(pemasukan)

    override suspend fun updatePemasukan(pemasukan: Pemasukan) = dao.update(pemasukan)

}