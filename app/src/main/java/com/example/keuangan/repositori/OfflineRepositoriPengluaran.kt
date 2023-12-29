package com.example.keuangan.repositori

import com.example.keuangan.data.Pengluaran
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriPengluaran(private val dao: com.example.keuangan.data.PengluaranDao): RepositoriPengluaran {
    override fun getAllPengluaranStream(): Flow<List<Pengluaran>> = dao.getAllPengluaran()

    override fun getPengluaranStream(id: Int): Flow<Pengluaran?> = dao.getPengluaran(id)

    override suspend fun insertPengluaran(pengluaran: Pengluaran) = dao.insert(pengluaran)
    override suspend fun deletePengluaran(pengluaran: Pengluaran) = dao.delete(pengluaran)

    override suspend fun updatePengluaran(pengluaran: Pengluaran) = dao.update(pengluaran)

}