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
        } catch (e: Exception){
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
}