package com.example.finaltaskkotlin.model

import android.os.Parcel
import android.os.Parcelable

data class Movie(val title: String?, val image: String?, val rating: Double, val releaseYear: Int,
                 val genre: Array<String>?): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.createStringArray()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(image)
        parcel.writeDouble(rating)
        parcel.writeInt(releaseYear)
        parcel.writeStringArray(genre)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (genre != null) {
            if (other.genre == null) return false
            if (!genre.contentEquals(other.genre)) return false
        } else if (other.genre != null) return false

        return true
    }

    override fun hashCode(): Int {
        return genre?.contentHashCode() ?: 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

