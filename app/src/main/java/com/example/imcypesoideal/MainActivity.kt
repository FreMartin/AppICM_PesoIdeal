package com.example.imcypesoideal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    lateinit var app : TextView
    lateinit var peso : TextView
    lateinit var estatura : TextView
    lateinit var resultado : TextView
    lateinit var logo : ImageView
    lateinit var valKilos : EditText
    lateinit var valMetros : EditText
    lateinit var resultadoImc : EditText
    lateinit var femenino : CheckBox
    lateinit var masculino : CheckBox
    lateinit var imc : Button
    lateinit var pesoIdeal : Button
    lateinit var borrar : Button
    var pi = 0.0
    var piup = 0.0
    var pidown = 0.0
    var f1 = 2.25
    var f2 = 45
    var m1 = 2.7
    var m2 = 47.75
    var bandera = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = findViewById(R.id.tvTitulo)
        peso  = findViewById(R.id.peso)
        estatura = findViewById(R.id.estatura)
        resultado = findViewById(R.id.tvResultado)
        logo = findViewById(R.id.logo)
        valKilos = findViewById(R.id.pesoValor)
        valMetros = findViewById(R.id.estaturaValor)
        resultadoImc = findViewById(R.id.etResultado)
        femenino = findViewById(R.id.fem)
        masculino = findViewById(R.id.mas)
        imc = findViewById(R.id.btnImc)
        pesoIdeal = findViewById(R.id.btnIdeal)
        borrar = findViewById(R.id.btnBorrar)

        femenino.setOnClickListener(){
            checkFem()
        }

        masculino.setOnClickListener(){
            checkMas()
        }

        imc.setOnClickListener(){
            imc()
        }

        pesoIdeal.setOnClickListener(){
            pesoIdeal()
        }

        borrar.setOnClickListener(){
            borrar()
        }

    }

    fun imc(){
        if (valKilos.text.toString().trim().isEmpty()){
            valKilos.requestFocus()
            Toast.makeText(this, "Ingrese por favor el peso.",Toast.LENGTH_LONG).show()
        } else if (valMetros.text.toString().trim().isEmpty()){
            valKilos.requestFocus()
            Toast.makeText(this, "Ingrese por favor la estatura.",Toast.LENGTH_LONG).show()
        }else {
            val kg = (valKilos.text.toString()).toFloat()
            val m = (valMetros.text.toString()).toFloat()
            val bmi = (kg / (m * m))
            if (bmi < 18.0) {
                Toast.makeText(this, "Tu IMC es: $bmi \n Bajo peso", Toast.LENGTH_LONG).show()
                resultadoImc.setText(bmi.toString())
            } else if (bmi < 24.9) {
                Toast.makeText(this, "Tu IMC es: $bmi \n Peso normal", Toast.LENGTH_LONG).show()
                resultadoImc.setText(bmi.toString())
            } else if (bmi < 29.9) {
                Toast.makeText(this, "Tu IMC es: $bmi \n Sobrepeso", Toast.LENGTH_LONG).show()
                resultadoImc.setText(bmi.toString())
            } else if (bmi < 34.9) {
                Toast.makeText(this, "Tu IMC es: $bmi \n Obesidad", Toast.LENGTH_LONG).show()
                resultadoImc.setText(bmi.toString())
            } else if (bmi > 35) {
                Toast.makeText(this, "Tu IMC es: $bmi \n Muy obeso", Toast.LENGTH_LONG).show()
                resultadoImc.setText(bmi.toString())
            }
        }
    }

    fun borrar(){
        valKilos.setText("")
        valKilos.requestFocus()
        valMetros.setText("")
        resultadoImc.setText("")
        pi = 0.0
        piup = 0.0
        pidown = 0.0
        femenino.isEnabled = true
        masculino.isEnabled = true
        femenino.isChecked = false
        masculino .isChecked = false
        bandera = true
    }

    fun pesoIdeal(){
        var kg = 0.0
        var m = 0.0
        if (valKilos.text.toString().trim().isEmpty()){
            valKilos.requestFocus()
            Toast.makeText(this, "Ingrese por favor el peso.",Toast.LENGTH_LONG).show()
            bandera = false
        }else if (valMetros.text.toString().trim().isEmpty()){
            valKilos.requestFocus()
            Toast.makeText(this, "Ingrese por favor la estatura.",Toast.LENGTH_LONG).show()
            bandera = false
        }else if(!masculino.isChecked && !femenino.isChecked){
            Toast.makeText(this, "Seleccione por favor un género.",Toast.LENGTH_LONG).show()
            bandera = false
        } else{
            kg = (valKilos.text.toString()).toFloat().toDouble()
            m = (valMetros.text.toString()).toFloat().toDouble()
            if (femenino.isChecked){
                masculino.isEnabled = false
                pi = (((((100*m) - 152.4) / 2.54) * f1) + f2)
                bandera = true
            } else if (masculino.isChecked){
                femenino.isEnabled = false
                pi = (((((100*m) - 152.4) / 2.54) * m1) + m2)
                bandera = true
            }

        }
        if (bandera){
            piup = (pi * 0.10) + pi
            Toast.makeText(this, "Resultado del peso ideal $pi \n", Toast.LENGTH_LONG).show()
            resultadoImc.setText(pi.toString())
            pidown = (pi - (pi * 0.10))
            if (kg > piup){
                Toast.makeText(this, "Estás arriba de tu peso ideal",Toast.LENGTH_LONG).show()
            }else if (kg in pidown..piup){
                Toast.makeText(this, "Estás en tu peso ideal",Toast.LENGTH_LONG).show()
            }else if(kg < pidown){
                Toast.makeText(this, "Estás abajo de tu peso ideal",Toast.LENGTH_LONG).show()
            }
        }


    }

    fun checkFem(){
        femenino.isChecked = true
        masculino.isChecked = false
        Toast.makeText(this, "Femenino",Toast.LENGTH_LONG).show()
    }

    fun checkMas(){
        masculino.isChecked = true
        femenino.isChecked = false
        Toast.makeText(this, "Masculino",Toast.LENGTH_LONG).show()
    }
}