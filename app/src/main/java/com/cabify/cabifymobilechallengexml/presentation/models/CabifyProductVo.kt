package com.cabify.cabifymobilechallengexml.presentation.models

import android.os.Parcel
import android.os.Parcelable
import com.cabify.cabifymobilechallengexml.domain.ProductCode
import com.cabify.cabifymobilechallengexml.domain.mappers.toProductCode

data class CabifyProductVo(
    val id: String,
    val code: ProductCode,
    val name: String,
    val price: Float,
    var count: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString()?.toProductCode() ?: ProductCode.NONE,
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeFloat(price)
        parcel.writeInt(count)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<CabifyProductVo> {
        override fun createFromParcel(parcel: Parcel): CabifyProductVo {
            return CabifyProductVo(parcel)
        }

        override fun newArray(size: Int): Array<CabifyProductVo?> {
            return arrayOfNulls(size)
        }
    }

}