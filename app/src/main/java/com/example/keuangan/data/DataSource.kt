package com.example.keuangan.data

import com.example.keuangan.R

object DataSource {
    val pemasukanList = listOf(
        Pemasukan("2023-01-01", 1000.0, "Gaji"),
        Pemasukan("2023-01-05", 500.0, "Bonus"),
        // ... tambahkan data pemasukan lainnya
    )

    val pengeluaranList = listOf(
        Pengeluaran("2023-01-02", 200.0, "Makan Siang"),
        Pengeluaran("2023-01-07", 50.0, "Transportasi"),
        // ... tambahkan data pengeluaran lainnya
    )
}





//object DataSource {
//    val tipe = listOf(
//        R.string.pemasukan,
//        R.string.pengeluaran
//    )
//
//    val kategori = listOf(
//        R.string.gaji,
//        R.string.orangtua,
//
//    )
//}