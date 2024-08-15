package com.example.libreria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import android.util.Log

class CRUDLibreria : AppCompatActivity() {

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_libreria),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crudlibreria)

        // BUSCAR Libreria
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val ciudad = findViewById<EditText>(R.id.input_ciudad)
            val direccion = findViewById<EditText>(R.id.input_direccion)
            val telefono = findViewById<EditText>(R.id.input_telefono)
            try {
                val libreria = DataBaseLibreria.tablaLibreria!!.consultarLibreriaPorID(id.text.toString().toInt())
                if (libreria == null) {
                    mostrarSnackbar("Libreria no encontrada")
                    id.setText("")
                    ciudad.setText("")
                    direccion.setText("")
                    telefono.setText("")
                } else {
                    id.setText(libreria.id_libreria.toString())
                    ciudad.setText(libreria.ciudad)
                    direccion.setText(libreria.direccion.toString())
                    telefono.setText(libreria.telefono.toString())
                    mostrarSnackbar("Libreria encontrada")
                }
            } catch (e: Exception) {
                Log.e("CRUDLibreria", "Error al buscar libreria", e)
            }
        }

        // FUNCION PARA CREAR UNA LIBRERIA
        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBDD.setOnClickListener {
            val ciudad = findViewById<EditText>(R.id.input_ciudad)
            val direccion = findViewById<EditText>(R.id.input_direccion)
            val telefono = findViewById<EditText>(R.id.input_telefono)
            try {
                val respuesta = DataBaseLibreria.tablaLibreria!!.crearLibreria(
                    ciudad.text.toString(),
                    direccion.text.toString(),
                    telefono.text.toString()
                )
                if (respuesta) mostrarSnackbar("Libreria Creada!")
            } catch (e: Exception) {
                Log.e("CRUDLibreria", "Error al crear libreria", e)
            }
        }

        // FUNCION PARA ACTUALIZAR UNA LIBRERIA
        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val ciudad = findViewById<EditText>(R.id.input_ciudad)
            val direccion = findViewById<EditText>(R.id.input_direccion)
            val telefono = findViewById<EditText>(R.id.input_telefono)
            try {
                val respuesta = DataBaseLibreria.tablaLibreria!!.actualizarLibreriaFormulario(
                    id.text.toString().toInt(),
                    ciudad.text.toString(),
                    direccion.text.toString(),
                    telefono.text.toString(),
                )
                if (respuesta) mostrarSnackbar("Libreria Actualizada!")
            } catch (e: Exception) {
                Log.e("CRUDLibreria", "Error al actualizar libreria", e)
            }
        }

        // FUNCION PARA ELIMINAR UNA LIBRERIA
        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            try {
                val respuesta = DataBaseLibreria.tablaLibreria!!.eliminarLibreriaFormulario(
                    id.text.toString().toInt()
                )
                if (respuesta) mostrarSnackbar("Libreria Eliminada!")
            } catch (e: Exception) {
                Log.e("CRUDLibreria", "Error al eliminar libreria", e)
            }
        }
    }
}
