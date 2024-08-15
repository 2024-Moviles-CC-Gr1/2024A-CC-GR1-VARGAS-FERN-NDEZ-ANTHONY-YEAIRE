package com.example.libreria

import android.os.Parcel
import android.os.Parcelable

class BLibro(
    var id_libro: Int,
    //var id_libreria: Int,
    var titulo: String,
    var autor: String,
    var genero: String,
    var precio: Double
): Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        //parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble()
    ) {
    }
    override fun toString(): String {
        return "$titulo $autor $genero $precio"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_libro)
        //parcel.writeInt(id_libreria)
        parcel.writeString(titulo)
        parcel.writeString(autor)
        parcel.writeString(genero)
        parcel.writeDouble(precio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BLibro> {
        override fun createFromParcel(parcel: Parcel): BLibro {
            return BLibro(parcel)
        }

        override fun newArray(size: Int): Array<BLibro?> {
            return arrayOfNulls(size)
        }
    }
}