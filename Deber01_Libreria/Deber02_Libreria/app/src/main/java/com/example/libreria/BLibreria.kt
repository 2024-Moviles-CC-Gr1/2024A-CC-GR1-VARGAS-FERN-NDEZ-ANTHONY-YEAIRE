package com.example.libreria

import android.os.Parcel
import android.os.Parcelable

class BLibreria (
    var id_libreria:Int,
    var ciudad:String,
    var direccion:String,
    var telefono:String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun toString(): String {
        return "$ciudad $direccion $telefono"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_libreria)
        parcel.writeString(ciudad)
        parcel.writeString(direccion)
        parcel.writeString(telefono)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BLibreria> {
        override fun createFromParcel(parcel: Parcel): BLibreria {
            return BLibreria(parcel)
        }

        override fun newArray(size: Int): Array<BLibreria?> {
            return arrayOfNulls(size)
        }
    }

}