package com.example.keuangan.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tblPengluaran")
data class Pengluaran(
    val dateAdded: Long,
    val nominal: Int,
    val deskripsi: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
