import java.io.File

class Libreria(
    val id: Int,
    var ciudad: String,
    var direccion: String,
    var dueno: String,
    var telefono: String,
    var areaRecreativa: String
) {
    override fun toString(): String {
        return "$id.\t$ciudad, $direccion (Dueño: $dueno, Área Recreativa: $areaRecreativa)"
    }

    companion object {
        val librerias = arrayListOf<Libreria>()

        fun listarLibrerias() {
            librerias.forEach { println(it) }
        }

        fun crearLibreria(
            ciudad: String,
            direccion: String,
            dueno: String,
            telefono: String,
            areaRecreativa: String
        ) {
            val id = if (librerias.isEmpty()) 1 else librerias.maxOf { it.id } + 1
            librerias.add(Libreria(id, ciudad, direccion, dueno, telefono, areaRecreativa))
        }

        fun borrarLibreria(id: Int) {
            val libreria = librerias.firstOrNull { it.id == id }
            if (libreria != null) {
                librerias.remove(libreria)
                // Eliminar libros asociados a esta librería
                Libro.borrarLibrosDeLibreria(id)
            }
        }

        fun actualizarLibreria(
            id: Int,
            ciudad: String?,
            direccion: String?,
            dueno: String?,
            telefono: String?,
            areaRecreativa: String?
        ) {
            val libreria = librerias.firstOrNull { it.id == id }
            if (libreria != null) {
                if (!ciudad.isNullOrBlank()) libreria.ciudad = ciudad
                if (!direccion.isNullOrBlank()) libreria.direccion = direccion
                if (!dueno.isNullOrBlank()) libreria.dueno = dueno
                if (!telefono.isNullOrBlank()) libreria.telefono = telefono
                if (!areaRecreativa.isNullOrBlank()) libreria.areaRecreativa = areaRecreativa
            }
        }

        fun leerDatosLibrerias() {
            val pathName = "librerias.txt"
            val miArchivo = File(pathName)
            if (miArchivo.exists()) {
                val lineasLista = mutableListOf<String>()
                miArchivo.useLines { lines -> lines.forEach { lineasLista.add(it) } }
                lineasLista.forEach {
                    val registro = it.split(',').toTypedArray()
                    val id = registro[0].toInt()
                    val ciudad = registro[1]
                    val direccion = registro[2]
                    val dueno = registro[3]
                    val telefono = registro[4]
                    val areaRecreativa = registro[5]
                    librerias.add(Libreria(id, ciudad, direccion, dueno, telefono, areaRecreativa))
                }
                println("${librerias.size} librerías cargadas")
            } else {
                println("No se encontró el archivo de librerías.")
            }
        }

        fun escribirDatosLibrerias() {
            val ruta = "librerias.txt"
            val archivo = File(ruta)
            archivo.printWriter().use { out ->
                librerias.forEach { out.println("${it.id},${it.ciudad},${it.direccion},${it.dueno},${it.telefono},${it.areaRecreativa}") }
            }
        }
    }
}