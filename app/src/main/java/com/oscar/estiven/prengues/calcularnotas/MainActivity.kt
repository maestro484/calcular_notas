package com.oscar.estiven.prengues.calcularnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var nombre: EditText
    private lateinit var notas: EditText
    private lateinit var porsentajes: EditText
    private lateinit var Retornar:Button
    private lateinit var Finalizar:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entrada)


        nombre = findViewById(R.id.nombre)
        notas = findViewById(R.id.notas)
        porsentajes = findViewById(R.id.porcentajes)
        Retornar = findViewById(R.id.Retornar)
        Finalizar = findViewById(R.id.Finalizar)



    }




}