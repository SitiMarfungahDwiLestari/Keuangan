package com.example.keuangan.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Pemasukan::class], [Pengluaran::class], version = 1)
abstract class DatabaseKeuangan : RoomDatabase() {
    abstract fun PemasukanDao(): PemasukanDao
    abstract fun PengluaranDao():PengluaranDao

    companion object {
        @Volatile
        private var instance: DatabaseKeuangan? = null

        fun getDatabase(context: Context): DatabaseKeuangan {
            return (instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DatabaseKeuangan::class.java,
                    "keuangan_database"
                ).build().also { instance = it }
            })
        }
    }
}
