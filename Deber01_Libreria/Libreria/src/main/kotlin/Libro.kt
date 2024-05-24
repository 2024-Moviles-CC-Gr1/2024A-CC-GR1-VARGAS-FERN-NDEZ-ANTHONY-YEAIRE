import java.io.File

class Libro(
    val isbn: String,
    var titulo: String,
    var autor: String,
    var genero: String,
    var estado: String,
    var precio: Double,
    var idLibreria: Int
) {
    override fun toString(): String {
        return "ISBN: $isbn, Título: $titulo, Autor: $autor, Género: $genero, Estado: $estado, Precio: $precio, ID Librería: $idLibreria"
    }

    companion object {
        val libros = arrayListOf<Libro>()

        fun listarLibrosLibreria(idLibreria: Int) {
            val listaLibros = libros.filter { it.idLibreria == idLibreria }
            listaLibros.forEach { println(it) }
        }

        fun crearLibro(
            isbn: String,
            titulo: String,
            autor: String,
            genero: String,
            estado: String,
            precio: Double,
            idLibreria: Int
        ) {
            libros.add(Libro(isbn, titulo, autor, genero, estado, precio, idLibreria))
        }

        fun borrarLibro(isbn: String) {
            val libro = libros.firstOrNull { it.isbn == isbn }
            if (libro != null) {
                libros.remove(libro)
            }
        }

        fun actualizarLibro(
            isbn: String,
            titulo: String?,
            autor: String?,
            genero: String?,
            estado: String?,
            precio: Double?,
            idLibreria: Int?
        ) {
            val libro = libros.firstOrNull { it.isbn == isbn }
            if (libro != null) {
                if (!titulo.isNullOrBlank()) libro.titulo = titulo
                if (!autor.isNullOrBlank()) libro.autor = autor
                if (!genero.isNullOrBlank()) libro.genero = genero
                if (!estado.isNullOrBlank()) libro.estado = estado
                if (precio != null) libro.precio = precio
                if (idLibreria != null) libro.idLibreria = idLibreria
            }
        }

        fun borrarLibrosDeLibreria(idLibreria: Int) {
            libros.removeAll { it.idLibreria == idLibreria }
        }

        fun leerDatosLibros() {
            val pathName = "libros.txt"
            val miArchivo = File(pathName)
            if (miArchivo.exists()) {
                val lineasLista = mutableListOf<String>()
                miArchivo.useLines { lines -> lines.forEach { lineasLista.add(it) } }
                lineasLista.forEach {
                    val registro = it.split(',').toTypedArray()
                    val isbn = registro[0]
                    val titulo = registro[1]
                    val autor = registro[2]
                    val genero = registro[3]
                    val estado = registro[4]
                    val precio = registro[5].toDouble()
                    val idLibreria = registro[6].toInt()
                    libros.add(Libro(isbn, titulo, autor, genero, estado, precio, idLibreria))
                }
                println("${libros.size} libros cargados")
            } else {
                println("No se encontró el archivo de libros.")
            }
        }

        fun escribirDatosLibros() {
            val ruta = "libros.txt"
            val archivo = File(ruta)
            archivo.printWriter().use { out ->
                libros.forEach { out.println("${it.isbn},${it.titulo},${it.autor},${it.genero},${it.estado},${it.precio},${it.idLibreria}") }
            }
        }
    }
}