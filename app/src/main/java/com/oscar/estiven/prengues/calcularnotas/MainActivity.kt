package com.oscar.estiven.prengues.calcularnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import kotlin.contracts.Returns

class MainActivity : AppCompatActivity() {

    //aqui declaramos todos las cosas que colocamos el la vista
    private lateinit var nombreIngresado: EditText
    private lateinit var notasIngresadas: EditText
    private lateinit var porsentajesIngresados: EditText
    private lateinit var siguiente: Button
    private lateinit var Finalizar: Button
    private lateinit var progreso: ProgressBar
    private lateinit var vistaPromedioFinal: TextView
    private lateinit var vistaNotaFinal: TextView
    private lateinit var Restart: Button

    private var porcentajeAcumulado = 0

    val listaDePorcentaje: MutableList<Int> = mutableListOf()

    val listaDeNotas: MutableList<Double> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entrada)

// aqui llamamos las cosas de claradas
        nombreIngresado = findViewById(R.id.nombreIngresado)
        notasIngresadas = findViewById(R.id.notasIngresadas)
        porsentajesIngresados = findViewById(R.id.porcentajesIngresados)
        siguiente = findViewById(R.id.siguinete)
        Finalizar = findViewById(R.id.Finalizar)
        progreso = findViewById(R.id.progreso)
        vistaPromedioFinal = findViewById(R.id.vistaPromedioFinal)
        vistaNotaFinal = findViewById(R.id.vistaNotaFinal)
        Restart = findViewById(R.id.Restart)



     Restart.setOnClickListener{

         vistaNotaFinal.visibility = View.VISIBLE
         vistaPromedioFinal.visibility = View.VISIBLE
          Finalizar.visibility = View.INVISIBLE
         Restart.visibility = View.INVISIBLE


         nombreIngresado.text.clear()
         notasIngresadas.text.clear()
         porsentajesIngresados.text.clear()
         progreso.progress = 0
         nombreIngresado.isEnabled = true
         vistaPromedioFinal.text = " "
         vistaNotaFinal.text = " "
        porcentajeAcumulado = 0
         listaDePorcentaje.clear()
         listaDeNotas.clear()


     }

        Finalizar.setOnClickListener {
            Restart.visibility = View.VISIBLE

            vistaNotaFinal.visibility = View.VISIBLE
            vistaPromedioFinal.visibility = View.VISIBLE

            vistaNotaFinal.text = "nota final : " + notaFinal()

            vistaPromedioFinal.text = "promedio: " + calcularpromedio()
        }

        siguiente.setOnClickListener {
            Finalizar.visibility = View.VISIBLE


            val nota = notasIngresadas.text.toString()
            val porcentaje = porsentajesIngresados.text.toString()
            val nombre = nombreIngresado.text.toString()


            if (validarVacio(nombre, nota, porcentaje)) {
                if (validarNombre(nombre) &&
                    validarNotas(nota.toDouble()) &&
                    validarPorcentaje((porcentaje.toInt()))
                ) {
                    listaDeNotas.add(nota.toDouble())
                    listaDePorcentaje.add(porcentaje.toInt())

                    porcentajeAcumulado += porcentaje.toInt()

                    actualizarProgress(porcentajeAcumulado)

                    nombreIngresado.isEnabled = false
                    notasIngresadas.text.clear()
                    porsentajesIngresados.text.clear()

                    mostrarMensaje("la nota fue ingresada correctamente")
                } else {
                    mostrarMensaje("verifique los datos ingresados")
                }
            } else {
                mostrarMensaje("Datos incompletos")
            }
        }
    }

    fun actualizarProgress(porcentaje: Int) {
        progreso.progress = porcentaje
        if (porcentaje >= 100) {
            Finalizar.isEnabled = true

        }
    }

    fun mostrarMensaje(mensaje: String) {
        Toast.makeText(
            this,
            mensaje,
            Toast.LENGTH_LONG
        ).show()
    }


    fun validarVacio(nombre: String, nota: String, porsentaje: String): Boolean {
        return !nombre.isNullOrEmpty() || nota.isNotEmpty() || porsentaje.isNullOrEmpty()
    }

    fun validarNombre(nombre: String): Boolean {
        return !nombre.matches(Regex(".\\d.*"))
    }

    fun validarNotas(nota: Double): Boolean {

        return (nota >= 0.0 && nota <= 5.0)
    }

    fun validarPorcentaje(porcentaje: Int): Boolean {
        return porcentajeAcumulado + porcentaje <= 100
    }

    fun calcularpromedio(): Double {

        var p = 0.0

        for (n in listaDeNotas) {

            p += n
        }

       return Math.round(( p / listaDeNotas.size) * 1000.00) / 1000.00
    }

    fun notaFinal(): Double {

        var notaFinal: Double = 0.0
        var contador = 0

        for (n in listaDePorcentaje) {
            notaFinal += (n * listaDePorcentaje[contador]) / 100
            contador++

        }
      return Math.round(notaFinal * 1000.00) / 1000.00
    }

}




















