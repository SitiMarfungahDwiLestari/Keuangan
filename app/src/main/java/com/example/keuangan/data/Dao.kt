package com.example.keuangan.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//
//@Dao
//interface Dao {
//        @Insert(onConflict = OnConflictStrategy.IGNORE)
//        suspend fun insert(keuangan: Keuangan)
//
//        @Update
//        suspend fun update(keuangan: Keuangan)
//
//        @Delete
//        suspend fun delete(keuangan: Keuangan)
//
//        @Query("SELECT * from tblPemasukan WHERE id = :id")
//        fun getKeuangan(id: Int): Flow<Keuangan>
//
//        @Query("SELECT * from tblPemasukan ORDER BY id ASC")
//        fun getAllKeuangan(): Flow<List<Keuangan>>
//}