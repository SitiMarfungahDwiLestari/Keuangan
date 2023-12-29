package com.example.keuangan.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PengluaranDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pengluaran: Pengluaran)

    @Update
    suspend fun update(pengluaran: Pengluaran)

    @Delete
    suspend fun delete(pengluaran: Pengluaran)

    @Query("SELECT * from tblPengluaran WHERE id = :id")
    fun getPengluaran(id: Int): Flow<Pengluaran>

    @Query("SELECT * from tblPengluaran ORDER BY id ASC")
    fun getAllPengluaran(): Flow<List<Pengluaran>>
}