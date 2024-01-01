package com.example.keuangan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.keuangan.nav.NavGraph
import com.example.keuangan.ui.theme.KeuanganTheme
import com.example.keuangan.util.PengeluaranViewModel
import com.example.keuangan.util.SharedViewModel



class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()
    private val pengeluaranViewModel: PengeluaranViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeuanganTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    NavGraph(
                        navController = navController,
                        sharedViewModel = sharedViewModel,
                        pengeluaranViewModel = pengeluaranViewModel
                    )
                }
            }
        }
    }
}
