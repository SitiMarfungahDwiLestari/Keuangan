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


class SharedViewModel: ViewModel() {
    fun saveData(
        pemasukan: pemasukan,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("pemasukan")
            .document(pemasukan.id)
        try{
            fireStoreRef.set(pemasukan)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveData(
        id: String,
        context: Context,
        data: (pemasukan) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("pemasukan")
            .document(id)
        try{
            fireStoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()){
                        val pemasukan = it.toObject<pemasukan>()!!
                        data(pemasukan)
                    } else{
                        Toast.makeText(context,"No ID Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    fun deleteData(
        id: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("pemasukan")
            .document(id)
        try{
            fireStoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    fun readAllData(context: Context, onDataReceived: (List<pemasukan>) -> Unit) =
        CoroutineScope(Dispatchers.IO).launch {
            val fireStoreRef = Firebase.firestore.collection("pemasukan")

            try {
                fireStoreRef.get()
                    .addOnSuccessListener { result ->
                        val dataList = mutableListOf<pemasukan>()

                        for (document in result) {
                            val id = document.id
                            val tanggal = document.getString("tanggal") ?: ""
                            val nominal = document.getLong("nominal")?.toInt() ?: 0
                            val kategori = document.getString("kategori") ?: ""

                            val pemasukan = pemasukan(id, tanggal, nominal, kategori)
                            dataList.add(pemasukan)
                        }

                        onDataReceived(dataList)
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(context, "Error reading data: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }


}