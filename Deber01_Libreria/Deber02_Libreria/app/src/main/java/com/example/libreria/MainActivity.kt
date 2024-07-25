package com.example.libreria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_main),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializar Base de Datos
        DataBaseLibreria.tablaLibreria = SQLiteHelperLibreria(
            this
        )
        DataBaseLibro.tablaLibro = SQLiteHelperLibro(
            this
        )

        val botonLibreria = findViewById<Button>(R.id.btn_libreria)
        botonLibreria.setOnClickListener {
            irActividad((CRUDLibreria::class.java))
        }

        val botonLibro = findViewById<Button>(R.id.btn_libro)
        botonLibro.setOnClickListener {
            irActividad((CRUDLibro::class.java))
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}