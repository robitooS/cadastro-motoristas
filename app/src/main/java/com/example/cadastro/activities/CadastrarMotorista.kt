package com.example.cadastro.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cadastro.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class CadastrarMotorista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastar_motorista)

        val cep = findViewById<EditText>(R.id.edtCep)

        val btnBuscar = findViewById<Button>(R.id.btnBuscarCep)
        val botaoAvancar = findViewById<ImageButton>(R.id.btnAvancar)


        botaoAvancar.setOnClickListener {
            val nome = findViewById<EditText>(R.id.edtNomeMoto).text.toString()
            val email = findViewById<EditText>(R.id.edtEmail).text.toString()
            val cpf = findViewById<EditText>(R.id.edtCpf).text.toString()
            val cnh = findViewById<EditText>(R.id.edtCnh).text.toString()
            val celular = findViewById<EditText>(R.id.edtNumeroCelular).text.toString()

            val logradouro = findViewById<EditText>(R.id.textLogradouro).text.toString()
            val bairro = findViewById<EditText>(R.id.textBairro).text.toString()
            val estado = findViewById<EditText>(R.id.textEstado).text.toString()
            val complemento = findViewById<EditText>(R.id.textComplemento).text.toString()
            val cidade = findViewById<EditText>(R.id.textCidade).text.toString()

            val condicao = nome.isEmpty() || email.isEmpty() || cpf.isEmpty() || cnh.isEmpty() ||
                    celular.isEmpty() || cep.toString().isEmpty() || logradouro.isEmpty() ||
                    bairro.isEmpty() || estado.isEmpty() || complemento.isEmpty() || cidade.isEmpty()

            if (condicao) {
                Toast.makeText(this, "Preencha todas as descrições", Toast.LENGTH_SHORT).show()
            } else {
                val pacote = Bundle().apply {
                    putString("nome", nome)
                    putString("email", email)
                    putString("cpf", cpf)
                    putString("cnh", cnh)
                    putString("celular", celular)
                    putString("cep", cep.text.toString())
                    putString("logradouro", logradouro)
                    putString("bairro", bairro)
                    putString("estado", estado)
                    putString("complemento", complemento)
                    putString("cidade", cidade)
                }

                val i = Intent(this, CadastrarCarros::class.java).apply {
                    putExtras(pacote)
                }
                startActivity(i)
                finish()
            }
        }

        btnBuscar.setOnClickListener {
            buscarDadosCep(cep.text.toString())
            Log.d("CEP", cep.text.toString())
        }

    }

    private fun buscarDadosCep(cep: String) {
        if (cep.length < 8 || cep.length > 8) {
            Toast.makeText(this, "O CEP deve conter 8 caracteres", Toast.LENGTH_SHORT).show()
        } else {
            val url = "https://viacep.com.br/ws/$cep/json/"

            CoroutineScope(Dispatchers.IO).launch {
                val response = URL(url).readText()

                withContext(Dispatchers.Main) {
                    processarResp(response)
                }
            }
        }
    }

    private fun processarResp(response: String) {
        Log.d("RESPOSTA", response)
        val responseJson = JSONObject(response)

        if (responseJson.has("erro") && responseJson.getBoolean("erro")) {
            Toast.makeText(this, "CEP não encontrado.", Toast.LENGTH_SHORT).show()
        } else {

            val logradouro = responseJson.getString("logradouro")
            val complemento = responseJson.getString("complemento")
            val bairro = responseJson.getString("bairro")
            val estado = responseJson.getString("estado")
            val cidade = responseJson.getString("localidade")

            findViewById<EditText>(R.id.textLogradouro).setText(logradouro)
            findViewById<EditText>(R.id.textComplemento).setText(complemento)
            findViewById<EditText>(R.id.textCidade).setText(bairro)
            findViewById<EditText>(R.id.textEstado).setText(estado)
            findViewById<EditText>(R.id.textBairro).setText(cidade)


        }


    }

}
