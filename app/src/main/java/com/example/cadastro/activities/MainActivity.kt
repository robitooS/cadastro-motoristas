package com.example.cadastro.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cadastro.database.Database
import com.example.cadastro.R
import com.example.cadastro.webservice.ClienteWS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val botaoCadastrarCarros = findViewById<Button>(R.id.btnCadastrar)
        val botaoSincronizar = findViewById<Button>(R.id.btnSincronizar)

        botaoCadastrarCarros.setOnClickListener {
            val i = Intent(this, CadastrarMotorista::class.java)
            startActivity(i)
        }

        botaoSincronizar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val motoristasNaoSincronizados =
                    Database.getInstance(this@MainActivity)!!
                        .motoristaDAO().getNaoSincronizado()

                if (motoristasNaoSincronizados.isEmpty()) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@MainActivity,
                            "Não há motoristas para sincronizar!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return@launch // encerra a coroutine aqui
                }

                val clienteWS = ClienteWS()

                for (m in motoristasNaoSincronizados) {
                    try {

                        Log.d("MOTORISTA DADOS -> ", m.toString())
                        clienteWS.enviarMotorista(m)
                        m.sincronizado = true
                        Database.getInstance(this@MainActivity)!!
                            .motoristaDAO().atualizarMotorista(m)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "Dados sincronizados com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}