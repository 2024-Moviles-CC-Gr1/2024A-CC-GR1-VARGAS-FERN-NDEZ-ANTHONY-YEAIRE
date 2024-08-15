package com.example.libreria

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelperLibro(
    contexto: Context? // this
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Crear una tabla
        val scriptSQLCrearTablaLibro =
            """
                CREATE TABLE LIBRO(
                    id_libro INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo VARCHAR(50),
                    autor VARCHAR(50),
                    genero VARCHAR(50),
                    precio DOUBLE
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaLibro)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun crearLibro(
        //id_libreria: Int,
        titulo: String,
        autor: String,
        genero: String,
        precio: Double
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        //valoresAGuardar.put("id_libreria", id_libreria)
        valoresAGuardar.put("titulo", titulo)
        valoresAGuardar.put("autor", autor)
        valoresAGuardar.put("genero", genero)
        valoresAGuardar.put("precio", precio)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "LIBRO", // nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarLibroFormulario(id_libro: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id_libro.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "LIBRO",
                "id_libro=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt()==-1) false else true
    }

    fun actualizarLibroFormulario(
        id_libro: Int,
        //id_libreria: Int,
        titulo: String,
        autor: String,
        genero: String,
        precio: Double
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        //valoresAActualizar.put("id_libreria", id_libreria)
        valoresAActualizar.put("titulo", titulo)
        valoresAActualizar.put("autor", autor)
        valoresAActualizar.put("genero", genero)
        valoresAActualizar.put("precio", precio)
        val parametrosConsultaActualizar = arrayOf(id_libro.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "LIBRO",
                valoresAActualizar,
                "id_libro=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt()==-1) false else true
    }

    fun consultarLibroPorID(id_libro: Int): BLibro? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM LIBRO WHERE id_libro = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(
            id_libro.toString()
        )
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )

        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<BLibro>()
        if (existeAlMenosUno) {
            do {
                val libro = BLibro(
                    resultadoConsultaLectura.getInt(0),
                    //resultadoConsultaLectura.getInt(1),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getString(3),
                    resultadoConsultaLectura.getDouble(4)
                )
                arregloRespuesta.add(libro)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return if(arregloRespuesta.size > 0) arregloRespuesta[0] else null
    }
}