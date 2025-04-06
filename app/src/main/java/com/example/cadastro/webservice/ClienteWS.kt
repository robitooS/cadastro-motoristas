package com.example.cadastro.webservice

import com.example.cadastro.model.Motorista
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import kotlin.jvm.Throws

class ClienteWS {

    @Throws(IOException::class, XmlPullParserException::class)
    fun enviarMotorista(m: Motorista) {

        val NAMESPACE = "http://localhost:8080/motorista"
        val METHOD_NAME = "syncBancoRequest"
        val URL = "http://10.0.2.2:8080/ws/motorista"
        val SOAP_ACTION = ""

        val soap = SoapObject(NAMESPACE, METHOD_NAME)

        fun addProp(name: String, value: Any?) {
            val prop = PropertyInfo().apply {
                this.name = name
                this.namespace = NAMESPACE
                this.value = value
                this.type = PropertyInfo.STRING_CLASS
            }
            soap.addProperty(prop)
        }

        // Dados do motorista
        addProp("nome", m.nome)
        addProp("cpf", m.cpf)
        addProp("cnh", m.cnh)
        addProp("email", m.email)
        addProp("celular", m.celular)
        addProp("cep", m.cep)
        addProp("logradouro", m.logradouro)
        addProp("complemento", m.complemento)
        addProp("cidade", m.cidade)
        addProp("estado", m.estado)
        addProp("bairro", m.bairro)

        // Dados do carro
        addProp("placa", m.placa)
        addProp("marca", m.marca)
        addProp("modelo", m.modelo)

        val anoProp = PropertyInfo().apply {
            name = "ano"
            namespace = NAMESPACE
            value = m.ano
            type = PropertyInfo.INTEGER_CLASS
        }
        soap.addProperty(anoProp)
        addProp("cor", m.cor)
        addProp("kmAtual", m.km)

        // Envelope SOAP
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER10).apply {
            setOutputSoapObject(soap)
        }

        HttpTransportSE(URL).call(SOAP_ACTION, envelope)

        val resposta = envelope.response
        println("Resposta: $resposta")
    }
}
