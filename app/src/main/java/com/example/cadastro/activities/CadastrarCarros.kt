package com.example.cadastro.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cadastro.database.Database
import com.example.cadastro.model.Motorista
import com.example.cadastro.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CadastrarCarros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastrar_carros)

        val placa = findViewById<EditText>(R.id.edtPlaca)
        val marca = findViewById<EditText>(R.id.edtMarca)
        val modelo = findViewById<EditText>(R.id.edtModelo)
        val ano = findViewById<EditText>(R.id.edtAno)
        val cor = findViewById<EditText>(R.id.edtCor)
        val km = findViewById<EditText>(R.id.edtKm)

        val pacote = intent.extras
        val nome = pacote!!.getString("nome")
        val email = pacote.getString("email")
        val cpf = pacote.getString("cpf")
        val cnh = pacote.getString("cnh")
        val celular = pacote.getString("celular")
        val cep = pacote.getString("cep")
        val logradouro = pacote.getString("logradouro")
        val bairro = pacote.getString("bairro")
        val estado = pacote.getString("estado")
        val complemento = pacote.getString("complemento")
        val cidade = pacote.getString("cidade")

        val botaoSalvar = findViewById<Button>(R.id.btnSalvarCadastro)


        botaoSalvar.setOnClickListener {

            val condicao =
                        placa.text.toString().isEmpty() ||
                        marca.text.toString().isEmpty() ||
                        modelo.text.toString().isEmpty() ||
                        ano.text.toString().isEmpty() ||
                        cor.text.toString().isEmpty() ||
                        km.text.toString().isEmpty()

            if (condicao) {
                Toast.makeText(this, "Preencha todas as descrições", Toast.LENGTH_SHORT).show()
            } else {
                val motorista = Motorista(
                    nome.toString(),
                    cpf.toString(),
                    cnh.toString(),
                    email.toString(),
                    celular.toString(),
                    cep.toString(),
                    logradouro.toString(),
                    complemento.toString(),
                    cidade.toString(),
                    estado.toString(),
                    bairro.toString(),
                    placa.text.toString(),
                    marca.text.toString(),
                    modelo.text.toString(),
                    ano.text.toString().toInt(),
                    cor.text.toString(),
                    km.text.toString(),
                    id = null
                )

                CoroutineScope(Dispatchers.IO).launch {
                    Database.getInstance(this@CadastrarCarros)!!.motoristaDAO().salvar(motorista)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@CadastrarCarros, "Motorista salvo com sucesso no banco de dados local", Toast.LENGTH_SHORT).show()
                        Log.d("MOTORISTA", motorista.toString())
                        finish()
                    }
                }
            }

        }
    }
}