package com.example.keuangan.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tblPemasukan")
data class Pemasukan(
    val dateAdded: Long,
    val nominal: Int,
    val kategori: String = "",
    val deskripsi: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)



