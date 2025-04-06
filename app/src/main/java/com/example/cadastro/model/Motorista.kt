package com.example.cadastro.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.ksoap2.serialization.KvmSerializable
import org.ksoap2.serialization.PropertyInfo
import java.util.Hashtable

@Entity
data class Motorista(
    var nome: String,
    var cpf: String,
    var cnh: String,
    var email: String,
    var celular: String,
    var cep: String,
    var logradouro: String,
    var complemento: String,
    var cidade: String,
    var estado: String,
    var bairro: String,

    var placa: String,
    var marca: String,
    var modelo: String,
    var ano: Int,
    var cor: String,
    var km: String,

    var sincronizado: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int?
) : KvmSerializable {

    override fun getProperty(index: Int): Any = when (index) {
        0 -> nome
        1 -> cpf
        2 -> cnh
        3 -> email
        4 -> celular
        5 -> cep
        6 -> logradouro
        7 -> complemento
        8 -> cidade
        9 -> estado
        10 -> bairro
        11 -> placa
        12 -> marca
        13 -> modelo
        14 -> ano
        15 -> cor
        16 -> km
        else -> "null"
    }

    override fun getPropertyCount(): Int = 17

    override fun setProperty(index: Int, value: Any?) {
        when (index) {
            0 -> nome = value.toString()
            1 -> cpf = value.toString()
            2 -> cnh = value.toString()
            3 -> email = value.toString()
            4 -> celular = value.toString()
            5 -> cep = value.toString()
            6 -> logradouro = value.toString()
            7 -> complemento = value.toString()
            8 -> cidade = value.toString()
            9 -> estado = value.toString()
            10 -> bairro = value.toString()
            11 -> placa = value.toString()
            12 -> marca = value.toString()
            13 -> modelo = value.toString()
            14 -> ano = value.toString().toInt()
            15 -> cor = value.toString()
            16 -> km = value.toString()
        }
    }

    override fun getPropertyInfo(index: Int, properties: Hashtable<*, *>?, info: PropertyInfo?) {
        if (info == null)
            return

        when (index) {
            0 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "nome"
            }
            1 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "cpf"
            }
            2 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "cnh"
            }
            3 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "email"
            }
            4 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "celular"
            }
            5 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "cep"
            }
            6 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "logradouro"
            }
            7 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "complemento"
            }
            8 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "cidade"
            }
            9 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "estado"
            }
            10 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "bairro" }
            11 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "placa"
            }
            12 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "marca"
            }
            13 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "modelo"
            }
            14 -> {
                info.type = PropertyInfo.INTEGER_CLASS
                info.name = "ano"
            }
            15 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "cor"
            }
            16 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "kmAtual"
            }
            else -> {}
        }
    }
}
