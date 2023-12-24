package com.example.keuangan.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tblPemasukan")
data class Pemasukan(
    val tanggal: String,
    val nominal: Double,
    val kategori: String
)

data class Pengeluaran(
    val tanggal: String,
    val nominal: Double,
    val deskripsi: String
)









//
//@Entity(tableName = "tblPemasukan")
//data class Keuangan(
//    val dateAdded: Long,
//    val nominal: Int,
//    val kategori: String = "",
//    val deskripsi: String,
//
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0
//)
//
//
//@Entity(tableName = "tblPengluaran")
//data class Keuangan1(
//    val dateAdded: Long,
//    val nominal: Int,
//    val deskripsi: String,
//
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0
//)

