package com.example.libreria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class CRUDLibro : AppCompatActivity() {
    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_libro),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crudlibro)

        // BUSCAR LIBRO
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd1)
        botonBuscarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_libro)
            val idLibreria = findViewById<EditText>(R.id.input_id_libreria)
            val titulo = findViewById<EditText>(R.id.input_titulo)
            val autor = findViewById<EditText>(R.id.input_autor)
            val genero = findViewById<EditText>(R.id.input_genero)
            val precio = findViewById<EditText>(R.id.input_precio)
            val libro = DataBaseLibro.tablaLibro!!
                .consultarLibroPorID(
                    id.text.toString().toInt()
                )
            if (libro == null) {
                mostrarSnackbar("Libro no encontrado")
                id.setText("")
                idLibreria.setText("")
                titulo.setText("")
                autor.setText("")
                genero.setText("")
                precio.setText("")
            } else {
                id.setText(libro.id_libro.toString())
                idLibreria.setText(libro.id_libreria)
                titulo.setText(libro.titulo)
                autor.setText(libro.autor)
                genero.setText(libro.genero)
                precio.setText(libro.precio.toString())
                mostrarSnackbar("Libro encontrado")
            }
        }

        // FUNCION PARA CREAR UN LIBRO
        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd1)
        botonCrearBDD.setOnClickListener {
            val idLibreria = findViewById<EditText>(R.id.input_id_libreria)
            val titulo = findViewById<EditText>(R.id.input_titulo)
            val genero = findViewById<EditText>(R.id.input_genero)
            val autor = findViewById<EditText>(R.id.input_autor)
            val precio = findViewById<EditText>(R.id.input_precio)
            val respuesta = DataBaseLibro.tablaLibro!!
                .crearLibro(
                    idLibreria.text.toString().toInt(),
                    titulo.text.toString(),
                    genero.text.toString(),
                    autor.text.toString(),
                    precio.text.toString().toDouble()
                )
            if (respuesta) mostrarSnackbar("Libro Creado!")
        }

        // FUNCION PARA ACTUALIZAR UN LIBRO
        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd1)
        botonActualizarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_libro)
            val idLibreria = findViewById<EditText>(R.id.input_id_libreria)
            val titulo = findViewById<EditText>(R.id.input_titulo)
            val genero = findViewById<EditText>(R.id.input_genero)
            val autor = findViewById<EditText>(R.id.input_autor)
            val precio = findViewById<EditText>(R.id.input_precio)
            val respuesta = DataBaseLibro.tablaLibro!!
                .actualizarLibroFormulario(
                    id.text.toString().toInt(),
                    idLibreria.text.toString().toInt(),
                    titulo.text.toString(),
                    genero.text.toString(),
                    autor.text.toString(),
                    precio.text.toString().toDouble()
                )
            if (respuesta) mostrarSnackbar("Libro Actualizado!")
        }

        // FUNCION PARA ELIMINAR UN LIBRO
        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd1)
        botonEliminarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_libro)
            val respuesta = DataBaseLibro.tablaLibro!!
                .eliminarLibroFormulario(
                    id.text.toString().toInt()
                )
            if (respuesta) mostrarSnackbar("Libro Eliminado!")
        }
    }
}