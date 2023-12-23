package com.example.keuangan.ui.halaman

import com.example.keuangan.R
import com.example.keuangan.navigasi.DestinasiNavigasi

object DetailsDestination : DestinasiNavigasi {
    override val route= "item_details"
    override val titleRes = R.string.detail_keuangan
    const val keuanganIdArg = "itemId"
    val routeWithArgs = "$route/{$keuanganIdArg}"
}