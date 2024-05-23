import java.util.Date

fun main(){
    println("Hola Mundo")
    // VARIABLES INMUTABLES (No se RE-ASIGNA "=")
    // Variable Inmutable
    val inmutable: String = "Anthony"
    // inmutable = "Lucas" // Error!

    var mutable: String = "Yeaire"
    // mutable = "Lucas" // Ok!

    //Analizar que variable se usará, val ó var

    // Duck Typing
    val ejemploVariable = "Anthony Vargas"
    val edadEjemplo = 22
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo // Error!

    //Variables Primitivas
    val nombreEstudiante: String = "Anthony Vargas"
    val sueldo:Double = 0.0
    val estadoCivil: Char = 'S'
    val mayorEdad:Boolean = true

    //Clases en Java
    val fechaNacimiento: Date = Date()

    //When (Switch)
    val estadoCivilWhen = "C"
    when(estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No" //if-else pequeño

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)
    // Named parameters
    // calcularSueldo(sueldo, tasa, bonoEspecial)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)

    val sumaUno = Suma(1,1) // new Suma (1,1) en KOTLIN no hay "new"
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)


    // Arreglos Estáticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico);

    // Arreglos Dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
    )
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // Operadores (Funciones)
    // Ayudan en la iteración de arreglos, sin necesidad de "for" o "while"
    // El código se vuelve más legible
    // FOR EACH = > Devuelve "nada" Unit
    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{valorActual: Int -> // -> = > (Funciones flecha, en Kotlin no se usa las funciones de flecha "gorda")
            println("Valor actual: ${valorActual}");
        }
    // "it" (en inglés "eso") Simplifica el elemento iterado
    arregloDinamico.forEach{println("Valor actual (it): ${it}")}

    //MAP -> Muta (Modifica, cambia) el arreglo
    // 1) Se envía el nuevo valor de la iteración
    // 2) Nos devuelve un NUEVO ARREGLO con valores de las iteraciones
    val respuestasMap: List<Double> = arregloDinamico
        .map {valorActual: Int ->
            return@map valorActual.toDouble()+100.00 //Transforma los elementos a "Double" y les suma 100
        }
    println(respuestasMap)
    val respuestaMapDos = arregloDinamico.map{it + 15} // Les suma 15 a todos los elementos
    println(respuestaMapDos)

    // Filter -> Filtrar el arreglo
    // 1) Devolver una expresión TRUE o FALSE
    // 2) Nuevo arreglo FILTRADO
    val respuestaFilter: List<Int> = arregloDinamico
        .filter{valorActual:Int ->
            // Expresión o Condición
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter{it <= 5}
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR, AND
    // OR -> ANY (Alguno cumple?)
    // AND -> All (Todos cumplen?)
    val respuestaAny: Boolean = arregloDinamico
        .any{valorActual:Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // True (Si hay al menos 1 número mayor a 5)
    val respuestaAll: Boolean = arregloDinamico
        .all{valorActual:Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // False (No todos los números son mayores a 5)

    // REDUCE -> Devolver al valor acumulado
    // Siempre empieza en cero (En Kotlin): ValorAcumulado=0
    // (1,2,3,4,5) -> Acumular "SUMAR" estos valores del arreglo
    // valorIteración1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteración1
    // valorIteración2 = valorIteración1 + 2 = 1 + 2 = 3 -> Iteración2
    // valorIteración3 = valorIteración2 + 3 = 3 + 3 = 6 -> Iteración3
    // valorIteración4 = valorIteración3 + 4 = 6 + 4 = 10 -> Iteración4
    // valorIteración5 = valorIteración4 + 5 = 10 + 5 = 15 -> Iteración5
    val respuestaReduce: Int = arregloDinamico
        .reduce{acumulado:Int, valorActual:Int ->
            return@reduce (acumulado+valorActual) // -> Cambiar o usar la lógica del negocio
        }
    println(respuestaReduce);
    // returnReduce acumulado + (itemCarrito.cantidad + itemCarrito.precio)


} // Termina la función Main

// void -> unit
fun imprimirNombre(nombre:String): Unit{
    println("Nombre: ${nombre}") // Template Strings
}

fun calcularSueldo(
    sueldo:Double, // Requerido
    tasa: Double = 12.00, // Opcional (Defecto)
    bonoEspecial:Double? = null // Opcional (nullable)
    // Variable? -> "?" Es Nullable (Es decir, en algún momento puede ser nulo)
):Double {
    // Int -> Int? (nullable)
    // String -> String? (nullable)
    // Date -> Date? (nullable)
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) * bonoEspecial
    }
}

// Clase Abstracta
abstract class NumerosJava{
    protected val numeroUno:Int
    private val numeroDos:Int
    constructor(
        uno:Int,
        dos:Int
    ){
        // Constructor primario
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros( // Constructor Primario
    // Caso 1) Parámetro normal
    // uno:Int, (parámetro (sin modificar acceso))

    // Caso 2) Parámetro y propiedad (Atributo)
    // private var uno: Int (Propiedad "instancia.uno")

    // Caso 3) Parámetro y propiedad pública (atributo)
    // var uno: Int (Propuedad "instancia.uno") (public)
    protected val numeroUno:Int, // instancia.numeroUno
    protected val numeroDos:Int, // instancia.numeroDos
){
    // Bloque del constructor primario
    init {
        println("Inicializando")
    }
}

//Enviar parámetros de una función a una clase (extensión)
class Suma( // Constructor primario
    unoParametro: Int, // Parámetro
    dosParametro: Int, // Parámetros
): Numeros( //Clase papá, Numeros (extendiendo)
    unoParametro,
    dosParametro
){
    public val soyPublicoExplicito:String = "Explícito" //Públicas
    val soyPublicoImplicito:String = "Implicito" //Públicas
    init{ // Bloque de Constructor primario
        this.numeroUno
        this.numeroDos
        numeroUno // this. OPCIONAL (propiedades, métodos)
        numeroDos // this. OPCIONAL (propiedades, métodos)
        this.soyPublicoExplicito
        soyPublicoImplicito // this. OPCIONAL (propiedades, métodos
    }

    // Métodos
    // public fun sumar():Int{(opcional "public")}

    // Constructores Secundarios
    constructor(
        uno:Int?,
        dos:Int
    ):this(
        if(uno==null) 0 else uno,
        dos
    )

    // Constructores Terciario
    constructor(
        uno:Int,
        dos:Int?
    ):this(
        uno,
        if(dos==null) 0 else dos,
    )

    // Constructores Cuarto
    constructor(
        uno:Int?,
        dos:Int?
    ):this(
        if(uno==null) 0 else uno,
        if(dos==null) 0 else dos,
    )

    fun sumar():Int{
        val total = numeroUno + numeroDos
        // Suma.agregarHistorial(total) ("Suma." o "NombreClase." es OPCIONAL)
        agregarHistorial(total)
        return total
    }
    companion object{ // Comparte entre todas las instancias, similar al Static
        // Funciones y Variables
        val pi = 3.14
        fun elevarAlCuadrado(num:Int):Int{
            return num * num
        }
        val historialSumas = arrayListOf<Int>() // Arreglo donde se guardarán las sumas realizadas
        fun agregarHistorial(valorTotalSuma:Int){
            historialSumas.add(valorTotalSuma)
        }
    }
}

