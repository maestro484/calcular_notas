package com.oscar.estiven.prengues.calcularnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //aqui declaramos todos las cosas que colocamos el la vista
    private lateinit var nombreIngresado: EditText
    private lateinit var notasIngresadas: EditText
    private lateinit var porsentajesIngresados: EditText
    private lateinit var Retornar: Button
    private lateinit var Finalizar: Button
    private lateinit var progreso: ProgressBar
    private lateinit var vistaPromedioFinal: TextView
    private lateinit var vistaNotaFinal: TextView


    private var porcentajeAcumulado  = 0

    val listaDePorcentaje: MutableList<Int> = mutableListOf()

    val listaDeNotas: MutableList<Double> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entrada)

// aqui llamamos las cosas de claradas
        nombreIngresado = findViewById(R.id.nombreIngresado)
        notasIngresadas = findViewById(R.id.notasIngresadas)
        porsentajesIngresados = findViewById(R.id.porcentajesIngresados)
        Retornar = findViewById(R.id.Retornar)
        Finalizar = findViewById(R.id.Finalizar)
        progreso = findViewById(R.id.progreso)
        vistaPromedioFinal = findViewById(R.id.vistaPromedioFinal)
        vistaNotaFinal = findViewById(R.id.vistaNotaFinal)

        Finalizar.setOnClickListener(){

               vistaNotaFinal.text = "nota final : " + porcentajeAcumulado

               vistaPromedioFinal.text = "promedio: "  + calcularpromedio()
           }

        Retornar.setOnClickListener {


            val nota = notasIngresadas.text.toString()
            val porcentaje = porsentajesIngresados.text.toString()
            val nombre = nombreIngresado.text.toString()


            if (validarVacio(nombre, nota, porcentaje)){
                if (validarNombre(nombre) &&
                    validarNotas(nota.toDouble()) &&
                    validarPorcentaje((porcentaje.toInt()))
                ){
                    listaDeNotas.add(nota.toDouble())
                    listaDePorcentaje.add(porcentaje.toInt())

                    porcentajeAcumulado += porcentaje.toInt()

                    actualizarProgress(porcentajeAcumulado)

                    nombreIngresado.isEnabled = true
                    notasIngresadas.text.clear()
                    porsentajesIngresados.text.clear()

                    mostrarMensaje("la nota fue ingresada correctamente")
                }else{
                    mostrarMensaje("verifique los datos ingresados")
                }
            }else{
                mostrarMensaje("Datos incompletos")
            }
        }
    }

    fun actualizarProgress(porcentaje : Int) {
        progreso.progress = porcentaje
        if (porcentaje >= 100){
            Finalizar.isEnabled = true

        }
    }
    fun mostrarMensaje(mensaje : String){
        Toast.makeText(this,
            mensaje,
            Toast.LENGTH_LONG).show()
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
    fun validarPorcentaje(porcentaje : Int ) : Boolean{
        return porcentajeAcumulado + porcentaje <=100
    }

fun calcularpromedio(): Double{

    var p = 0.0

    for(n in listaDeNotas){

        p += n
    }
    return p / listaDeNotas.size

 }

}



















