package com.example.libreria

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelperLibreria(
    contexto: Context?
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Crear una tabla
        val scriptSQLCrearTablaLibreria =
            """
                CREATE TABLE LIBRERIA(
                    id_libreria INTEGER PRIMARY KEY AUTOINCREMENT,
                    ciudad VARCHAR(50),
                    direccion VARCHAR(50),
                    telefono VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaLibreria)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun crearLibreria(
        ciudad: String,
        direccion: String,
        telefono: String?
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("ciudad", ciudad)
        valoresAGuardar.put("direccion", direccion)
        valoresAGuardar.put("telefono", telefono)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "LIBRERIA", // nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarLibreriaFormulario(id_auto: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id_auto.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "LIBRERIA",
                "id_libreria=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt()==-1) false else true
    }

    fun actualizarLibreriaFormulario(
        id_libreria: Int,
        ciudad: String,
        direccion: String,
        telefono: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("ciudad", ciudad)
        valoresAActualizar.put("direccion", direccion)
        valoresAActualizar.put("telefono", telefono)
        val parametrosConsultaActualizar = arrayOf(id_libreria.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "LIBRERIA",
                valoresAActualizar,
                "id_libreria=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt()==-1) false else true
    }

    fun consultarLibreriaPorID(id_libreria: Int): BLibreria? { //BLibreria
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM AUTO WHERE id_libreria = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(
            id_libreria.toString()
        )
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )

        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<BLibreria>() //BLibreria
        if (existeAlMenosUno) {
            do {
                val libreria = BLibreria( //BLibreria
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getString(3),
                )
                arregloRespuesta.add(libreria)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return if(arregloRespuesta.size > 0) arregloRespuesta[0] else null
    }
}