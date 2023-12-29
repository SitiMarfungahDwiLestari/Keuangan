package com.example.keuangan.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface PemasukanDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pemasukan: Pemasukan)

    @Update
    suspend fun update(pemasukan: Pemasukan)

    @Delete
    suspend fun delete(pemasukan: Pemasukan)

    @Query("SELECT * from tblPemasukan WHERE id = :id")
    fun getPemasukan(id: Int): Flow<Pemasukan>

    @Query("SELECT * from tblPemasukan ORDER BY id ASC")
    fun getAllPemasukan(): Flow<List<Pemasukan>>
}