package com.example.cadastro.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cadastro.model.Motorista

@Dao
interface  MotoristaDAO {
    @Insert

    fun salvar(obj: Motorista)

    @Query("SELECT * FROM Motorista WHERE sincronizado = 0")
    fun getNaoSincronizado(): List<Motorista>

    @Update
    fun atualizarMotorista(motorista: Motorista)
}