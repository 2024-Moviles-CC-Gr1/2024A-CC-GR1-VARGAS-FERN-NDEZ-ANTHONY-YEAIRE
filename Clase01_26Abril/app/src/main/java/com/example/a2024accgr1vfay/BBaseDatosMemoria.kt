package com.example.a2024accgr1vfay

class BBaseDatosMemoria {
    companion object{
        var arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Anthony", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Yeaire", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Galo", "c@c.com")
                )
        }
    }
}