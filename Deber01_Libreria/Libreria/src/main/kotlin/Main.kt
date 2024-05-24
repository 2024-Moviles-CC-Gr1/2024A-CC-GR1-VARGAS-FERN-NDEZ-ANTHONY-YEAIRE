fun main() {
    Libreria.leerDatosLibrerias()
    Libro.leerDatosLibros()

    while (true) {
        println("\n-- Menú Principal --")
        println("1. Menú Librería")
        println("2. Menú Libro")
        println("3. Salir")
        print("Seleccione una opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> menuLibreria()
            2 -> menuLibro()
            3 -> {
                println("Saliendo...")
                Libreria.escribirDatosLibrerias()
                Libro.escribirDatosLibros()
                break
            }
            else -> println("Opción inválida.")
        }
    }
}

fun menuLibreria() {
    while (true) {
        println("\n-- Menú Librería --")
        println("1. Agregar librería")
        println("2. Ver librerías")
        println("3. Eliminar librería")
        println("4. Actualizar librería")
        println("5. Regresar al menú principal")
        print("Seleccione una opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Ciudad: ")
                val ciudad = readLine().orEmpty()
                print("Dirección: ")
                val direccion = readLine().orEmpty()
                print("Dueño: ")
                val dueno = readLine().orEmpty()
                print("Teléfono: ")
                val telefono = readLine().orEmpty()
                print("Área Recreativa (descripción): ")
                val areaRecreativa = readLine().orEmpty()

                Libreria.crearLibreria(ciudad, direccion, dueno, telefono, areaRecreativa)
            }
            2 -> Libreria.listarLibrerias()
            3 -> {
                print("ID de la librería a eliminar: ")
                val id = readLine()?.toIntOrNull()
                if (id != null) {
                    Libreria.borrarLibreria(id)
                    println("Librería eliminada.")
                } else {
                    println("ID inválido.")
                }
            }
            4 -> {
                print("ID de la librería a actualizar: ")
                val id = readLine()?.toIntOrNull()
                if (id != null) {
                    print("Nueva ciudad (dejar en blanco para no cambiar): ")
                    val ciudad = readLine()
                    print("Nueva dirección (dejar en blanco para no cambiar): ")
                    val direccion = readLine()
                    print("Nuevo dueño (dejar en blanco para no cambiar): ")
                    val dueno = readLine()
                    print("Nuevo teléfono (dejar en blanco para no cambiar): ")
                    val telefono = readLine()
                    print("Nueva área recreativa (dejar en blanco para no cambiar): ")
                    val areaRecreativa = readLine()

                    Libreria.actualizarLibreria(id, ciudad, direccion, dueno, telefono, areaRecreativa)
                } else {
                    println("ID inválido.")
                }
            }
            5 -> break
            else -> println("Opción inválida.")
        }
    }
}

fun menuLibro() {
    while (true) {
        println("\n-- Menú Libro --")
        println("1. Agregar libro")
        println("2. Ver libros de una librería")
        println("3. Eliminar libro")
        println("4. Actualizar libro")
        println("5. Regresar al menú principal")
        print("Seleccione una opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("ISBN: ")
                val isbn = readLine().orEmpty()
                print("Título: ")
                val titulo = readLine().orEmpty()
                print("Autor: ")
                val autor = readLine().orEmpty()
                print("Género: ")
                val genero = readLine().orEmpty()
                print("Estado: ")
                val estado = readLine().orEmpty()
                print("Precio: ")
                val precio = readLine()?.toDoubleOrNull() ?: 0.0
                print("ID de la librería: ")
                val idLibreria = readLine()?.toIntOrNull() ?: 0

                Libro.crearLibro(isbn, titulo, autor, genero, estado, precio, idLibreria)
            }
            2 -> {
                print("ID de la librería: ")
                val idLibreria = readLine()?.toIntOrNull()
                if (idLibreria != null) {
                    Libro.listarLibrosLibreria(idLibreria)
                } else {
                    println("ID inválido.")
                }
            }
            3 -> {
                print("ISBN del libro a eliminar: ")
                val isbn = readLine().orEmpty()
                Libro.borrarLibro(isbn)
            }
            4 -> {
                print("ISBN del libro a actualizar: ")
                val isbn = readLine().orEmpty()
                print("Nuevo título (dejar en blanco para no cambiar): ")
                val titulo = readLine()
                print("Nuevo autor (dejar en blanco para no cambiar): ")
                val autor = readLine()
                print("Nuevo género (dejar en blanco para no cambiar): ")
                val genero = readLine()
                print("Nuevo estado (dejar en blanco para no cambiar): ")
                val estado = readLine()
                print("Nuevo precio (dejar en blanco para no cambiar): ")
                val precio = readLine()?.toDoubleOrNull()
                print("Nueva ID de la librería (dejar en blanco para no cambiar): ")
                val idLibreria = readLine()?.toIntOrNull()

                Libro.actualizarLibro(isbn, titulo, autor, genero, estado, precio, idLibreria)
            }
            5 -> break
            else -> println("Opción inválida.")
        }
    }
}