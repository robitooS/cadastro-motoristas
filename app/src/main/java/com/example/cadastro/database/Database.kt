package com.example.cadastro.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cadastro.model.Motorista
import com.example.cadastro.dao.MotoristaDAO

@androidx.room.Database(entities = [Motorista::class], version = 3)
    abstract class Database: RoomDatabase() {

        abstract fun motoristaDAO(): MotoristaDAO

        companion object {

            private var database: Database? = null
            private val DATABASE = "MOTORISTA"

            fun getInstance(context: Context): Database? {
                if(database == null) {
                    return criaBanco(context)
                } else {
                    return database
                }
            }

            private fun criaBanco(context: Context): Database? {
                return Room.databaseBuilder(
                    context, Database::class.java, DATABASE
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }

