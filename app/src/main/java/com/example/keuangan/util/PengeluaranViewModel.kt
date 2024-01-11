package com.example.keuangan.util

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PengeluaranViewModel: ViewModel() {
    fun saveDataKeluar(
        pengeluaran: pengeluaran,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStorePengeluaran = Firebase.firestore
            .collection("pengeluaran")
            .document(pengeluaran.id)
        try{
            fireStorePengeluaran.set(pengeluaran)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveDataKeluar(
        id: String,
        context: Context,
        data: (pengeluaran) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStorePengeluaran = Firebase.firestore
            .collection("pengeluaran")
            .document(id)
        try{
            fireStorePengeluaran.get()
                .addOnSuccessListener {
                    if (it.exists()){
                        val pengeluaran = it.toObject<pengeluaran>()!!
                        data(pengeluaran)
                    } else{
                        Toast.makeText(context,"No ID Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    fun deleteDataKeluar(
        id: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStorePengeluaran = Firebase.firestore
            .collection("pengeluaran")
            .document(id)
        try{
            fireStorePengeluaran.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    fun readAllData(context: Context, onDataReceived: (List<pengeluaran>) -> Unit) =
        CoroutineScope(Dispatchers.IO).launch {
            val fireStoreRef = Firebase.firestore.collection("pengeluaran")

            try {
                fireStoreRef.get()
                    .addOnSuccessListener { result ->
                        val listdata = mutableListOf<pengeluaran>()

                        for (document in result) {
                            val id = document.id
                            val tanggal = document.getString("tanggal") ?: ""
                            val nominalpengeluaran = document.getLong("nominalpengeluaran")?.toInt() ?: 0
                            val deskripsi = document.getString("deskripsi") ?: ""

                            val pengeluaran = pengeluaran(id, tanggal, nominalpengeluaran, deskripsi)
                            listdata.add(pengeluaran)
                        }

                        onDataReceived(listdata)
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(context, "Error reading data: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
}