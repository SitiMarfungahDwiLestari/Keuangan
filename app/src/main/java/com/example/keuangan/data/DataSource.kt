package com.example.keuangan.data

import com.example.keuangan.R

object DataSource {
    var pemasukanList = mutableListOf(
        Pemasukan("2023-01-01", 1000.0, "Gaji"),
        Pemasukan("2023-01-05", 500.0, "Bonus"),
        // ... tambahkan data pemasukan lainnya
    )

    var pengeluaranList = mutableListOf(
        Pengeluaran("2023-01-02", 200.0, "Makan Siang"),
        Pengeluaran("2023-01-07", 50.0, "Transportasi"),
        // ... tambahkan data pengeluaran lainnya
    )

    fun tambahPemasukan(tanggal: String, nominal: Double, deskripsi: String) {
        pemasukanList.add(Pemasukan(tanggal, nominal, deskripsi))
    }

    fun tambahPengeluaran(tanggal: String, nominal: Double, deskripsi: String) {
        pengeluaranList.add(Pengeluaran(tanggal, nominal, deskripsi))
    }
}